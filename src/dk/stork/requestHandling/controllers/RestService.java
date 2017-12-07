package dk.stork.requestHandling.controllers;

import com.google.gson.Gson;
import dk.stork.entities.EntityFactory;
import dk.stork.entities.Group;
import dk.stork.entities.User;
import dk.stork.exceptions.EntityNotFoundException;
import dk.stork.requestHandling.communicationObjects.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Johannes Ernstsen
 */
@RestController
public class RestService {

    private final Gson gson = new Gson();

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginRequest login(@RequestBody String loginString) {
        LoginRequest loginRequest = gson.fromJson(loginString, LoginRequest.class);

        User user = EntityFactory.getUserFromEmail(loginRequest.getMail());

        if (user != null) {
            if (user.getPassword().equalsIgnoreCase(loginRequest.getPassword())) {
                String sessionId = EntityFactory.login(user);
                loginRequest.setUserId(user.getId());
                loginRequest.setSessionId(sessionId);
            } else {
                throw new EntityNotFoundException("No user with this combination of mail and password");
            }
        } else {
            throw new EntityNotFoundException("No user with this combination of mail and password");
        }

        return loginRequest;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public LogoutRequest logout(@RequestBody String logoutString) {

        LogoutRequest logoutRequest = gson.fromJson(logoutString, LogoutRequest.class);
        User user = EntityFactory.getModelObject(logoutRequest.getUserId(), User.class);
        if (user != null) {
            user.setSessionId("");
            user.save();
            logoutRequest.setSuccess(true);
        } else {
            logoutRequest.setSuccess(false);
        }
        logoutRequest.setSessionId("");
        return logoutRequest;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RegisterUserRequest registerUser(@RequestBody String registerUserString) {
        RegisterUserRequest req = gson.fromJson(registerUserString, RegisterUserRequest.class);

        if (EntityFactory.getUserFromEmail(req.getMail()) != null) {
            throw new NotLoggedInException("User already exists");
        }

        int id = EntityFactory.userFromRegisterRequest(req);

        String sessionId = EntityFactory.login(id);
        req.setUserId(id);
        req.setPassword("");
        req.setSessionId(sessionId);

        return req;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
    public void updateLocation(@RequestBody String updateLocationString) {
        UpdateLocationRequest req = gson.fromJson(updateLocationString, UpdateLocationRequest.class);

        User user = EntityFactory.getModelObject(req.getUserId(), User.class);

        if (user == null) {
            throw new EntityNotFoundException("No user found for id or sessionId");
        }
        String sessionId = user.getSessionId();
        if (sessionId == null || !sessionId.equals(req.getSessionId())) {
            throw new NotLoggedInException("No active session for user");
        }

        user.setLocation(gson.toJson(req.getLocation()));
        user.save();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/friend", method = RequestMethod.POST)
    public void friendChange(@RequestBody String body) {
        FriendChangeRequest req = gson.fromJson(body, FriendChangeRequest.class);

        User user = EntityFactory.getModelObject(req.getUserId(), User.class);

        if (user == null) {
            throw new EntityNotFoundException("No user found for id or sessionId");
        }
        String sessionId = user.getSessionId();
        if (sessionId == null || !sessionId.equals(req.getSessionId())) {
            throw new NotLoggedInException("No active session for user");
        }

        List<User> friends = EntityFactory.getUsers(req.getFriends());
        Set<User> existingFriendSet = user.getFriends();
        switch (req.getAction()) {
            case ADD:
                existingFriendSet.addAll(friends);
                break;
            case REMOVE:
                existingFriendSet.removeAll(friends);
                break;
            default:
                throw new RuntimeException("Malformed Body - no action found");
        }
        user.setFriends(existingFriendSet);
        user.save();

    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/changeUser", method = RequestMethod.POST)
    public ChangeUserRequest changeUser(@RequestBody String body) {
        ChangeUserRequest req = gson.fromJson(body, ChangeUserRequest.class);

        User user = EntityFactory.getModelObject(req.getUserId(), User.class);

        String newPassword = req.getNewPassword();
        String name = req.getName();

        boolean hasNewPassword = newPassword != null && !newPassword.isEmpty() && !user.getPassword().equals(newPassword);
        boolean passwordIsCorrect = user.getPassword().equals(req.getPassword());
        boolean sessionsIsActive = user.getSessionId() != null && user.getSessionId().equals(req.getSessionId());

        if (!(passwordIsCorrect && sessionsIsActive)) {
            throw new NotLoggedInException("Permission Denied");
        }

        boolean userNameHasChanged = name != null && !name.isEmpty() && !name.equals(user.getName());

        boolean hasChanged = false;

        if (hasNewPassword) {
            user.setPassword(newPassword);
            req.setSessionId(EntityFactory.login(user));
            hasChanged = true;
        }
        if (userNameHasChanged) {
            user.setName(req.getName());
            hasChanged = true;
        }
        if (hasChanged) {
            user.save();
        }
        req.setPassword(newPassword);
        req.setNewPassword("");
        return req;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/changeGroupActivation", method = RequestMethod.POST)
    public void changeGroupActivation(@RequestBody String body) {
        GroupChangeActivationRequest req = gson.fromJson(body, GroupChangeActivationRequest.class);
        User user = EntityFactory.getModelObject(req.getUserId(), User.class);

        if (user.getSessionId() == null || !user.getSessionId().equals(req.getSessionId())) {
            throw new NotLoggedInException("No active session for user");
        }

        Set<Group> activeGroups = user.getActiveGroups();
        activeGroups.addAll(EntityFactory.getGroups(req.getAdd()));
        activeGroups.removeAll(EntityFactory.getGroups(req.getRemove()));
        user.save();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/changeGroup")
    public void changeGroup(@RequestBody String body) {
        ChangeGroupRequest req = gson.fromJson(body, ChangeGroupRequest.class);
        User user = EntityFactory.getModelObject(req.getUserId(), User.class);

        if (user.getSessionId() == null || !user.getSessionId().equals(req.getSessionId())) {
            throw new NotLoggedInException("No active session for user");
        }
        ArrayList<User> affectedUsers = new ArrayList<>();
        Group group;
        if (req.getId() == 0) {
            group = new Group();
            group.setOwner(user);
            group.setMembers(new HashSet<User>(Collections.singletonList(user)));
            group.setName(req.getName());
            affectedUsers.add(user);
        } else {
            group = EntityFactory.getModelObject(req.getId(), Group.class);
            if (group == null) {
                throw new EntityNotFoundException("No group with given Id");
            }
            if (!group.getOwner().equals(user)) {
                throw new NotLoggedInException("Not group owner");
            }
        }

        Set<User> members = group.getMembers();


        if (req.getAdd() != null) {
            List<User> usersToAdd = EntityFactory.getUsers(req.getAdd());
            members.addAll(usersToAdd);
            affectedUsers.addAll(usersToAdd);
        }

        if (req.getRemove() != null) {
            List<User> usersToRemove = EntityFactory.getUsers(req.getRemove());
            members.removeAll(usersToRemove);
            affectedUsers.addAll(usersToRemove);
        }

        group.save();
        EntityFactory.refreshUsers(affectedUsers);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public UserObject getUser(@RequestParam("sessionId") String sessionId, @RequestParam("userId") int userId) {

        User user = EntityFactory.getModelObject(userId, User.class);
        if (user.getSessionId() == null || !user.getSessionId().equals(sessionId)) {
            throw new NotLoggedInException("No active session for user");
        }
        return user.createUserObject();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/getGroups", method = RequestMethod.GET)
    public GroupsResponse getGroups(@RequestParam("sessionId") String sessionId, @RequestParam("userId") int userId) {
        User user = EntityFactory.getModelObject(userId, User.class);
        if (user == null) {
            throw new EntityNotFoundException("No user found for id or sessionId");
        }
        if (user.getSessionId() == null || !user.getSessionId().equals(sessionId)) {
            throw new NotLoggedInException("No active session for user");
        }
        List<GroupObject> groupObjects = new ArrayList<>();
        for (Group group : user.getGroups()) {
            List<FriendObject> includedFriends = new ArrayList<>();
            for (User groupMember : group.getMembers()) {

                boolean notMe = !user.equals(groupMember);
                boolean hasValidLocation = groupMember.getLocation() != null && !groupMember.getLocation().isEmpty();
                boolean hasGroupAsActive = groupMember.getActiveGroups() != null && groupMember.getActiveGroups().contains(group);
                boolean shouldIncludeLocation = hasValidLocation && hasGroupAsActive;
                if (notMe) {
                    Location location = gson.fromJson(groupMember.getLocation(), Location.class);
                    includedFriends.add(new FriendObject(groupMember.getId(), groupMember.getName(), shouldIncludeLocation ? location : null));
                }
            }
            GroupObject groupObject = new GroupObject(group.getId(), group.getName(), includedFriends, group.getOwner().getId());
            groupObjects.add(groupObject);
        }
        return new GroupsResponse(groupObjects);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/getFriends", method = RequestMethod.GET)
    public UsersResponse getFriends(@RequestParam("sessionId") String sessionId, @RequestParam("userId") int userId) {
        User user = EntityFactory.getModelObject(userId, User.class);
        if (user == null) {
            throw new EntityNotFoundException("No user found for id or sessionId");
        }
        if (user.getSessionId() == null || !user.getSessionId().equals(sessionId)) {
            throw new NotLoggedInException("No active session for user");
        }
        ArrayList<PublicUserObject> friends = new ArrayList<>();
        for (User friend : user.getFriends()) {
            friends.add(friend.createFriendObject());
        }
        return new UsersResponse(friends);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public UsersResponse getUsers() {
        return new UsersResponse(EntityFactory.getAllUsersAsPublicUserObjects());
    }

}
