package dk.stork.requestHandling.controllers;

import com.google.gson.Gson;
import dk.stork.entities.EntityFactory;
import dk.stork.entities.Group;
import dk.stork.entities.User;
import dk.stork.exceptions.EntityNotFoundException;
import dk.stork.requestHandling.communicationObjects.RegisterUserRequest;
import dk.stork.requestHandling.communicationObjects.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author Johannes Ernstsen
 */
@RestController
public class RestService {

    private final Gson gson = new Gson();

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginRequest login(@RequestBody String loginString) {
        EntityFactory entityFactory = new EntityFactory();
        LoginRequest loginRequest = gson.fromJson(loginString, LoginRequest.class);

        User user = entityFactory.getUserFromEmail(loginRequest.getMail());

        if (user != null) {
            if (user.getPassword().equalsIgnoreCase(loginRequest.getPassword())) {
                String sessionId = entityFactory.login(user);
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
        EntityFactory entityFactory = new EntityFactory();

        LogoutRequest logoutRequest = gson.fromJson(logoutString, LogoutRequest.class);
        User user = entityFactory.getModelObject(logoutRequest.getUserId(), User.class);
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

        EntityFactory entityFactory = new EntityFactory();
        int id = entityFactory.userFromRegisterRequest(req);

        String sessionId = entityFactory.login(id);
        req.setUserId(id);
        req.setPassword("");
        req.setSessionId(sessionId);

        return req;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
    public void updateLocation(@RequestBody String updateLocationString) {
        UpdateLocationRequest req = gson.fromJson(updateLocationString, UpdateLocationRequest.class);

        EntityFactory entityFactory = new EntityFactory();
        User user = entityFactory.getModelObject(req.getUserId(), User.class);

        if (user == null) {
            throw new EntityNotFoundException("No user found for id or sessionId");
        }
        String sessionId = user.getSessionId();
        if (sessionId == null || !sessionId.equals(req.getSessionId())) {
            throw new NotLoggedInException("No active session for user");
        }

        user.setLocation(gson.toJson(req.getLocation()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/friend", method = RequestMethod.POST)
    public void friendChange(@RequestBody String body) {
        FriendChangeRequest req = gson.fromJson(body, FriendChangeRequest.class);

        EntityFactory entityFactory = new EntityFactory();
        User user = entityFactory.getModelObject(req.getUserId(), User.class);

        if (user == null) {
            throw new EntityNotFoundException("No user found for id or sessionId");
        }
        String sessionId = user.getSessionId();
        if (sessionId == null || !sessionId.equals(req.getSessionId())) {
            throw new NotLoggedInException("No active session for user");
        }

        List<User> friends = entityFactory.getUsers(req.getFriends());
        switch (req.getAction()) {
            case ADD:
                user.addFriends(friends);
                break;
            case REMOVE:
                user.removeFriends(friends);
                break;
            default:
                throw new RuntimeException("Malformed Body - no action found");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/changeUser", method = RequestMethod.POST)
    public ChangeUserRequest changeUser(@RequestBody String body) {
        ChangeUserRequest req = gson.fromJson(body, ChangeUserRequest.class);

        EntityFactory entityFactory = new EntityFactory();
        User user = entityFactory.getModelObject(req.getUserId(), User.class);
        boolean hasNewPassword = !req.getNewPassword().isEmpty() && !user.getPassword().equals(req.getNewPassword());
        boolean passwordIsCorrect = user.getPassword().equals(req.getPassword());
        boolean sessionsIsActive = user.getSessionId().equals(req.getSessionId());
        boolean hasChanged = false;
        if (hasNewPassword && passwordIsCorrect && sessionsIsActive) {
            req.setSessionId(entityFactory.login(user));
            hasChanged = true;
        }
        boolean userNameHasChanged = !user.getName().equals(req.getName());
        if (userNameHasChanged && passwordIsCorrect && sessionsIsActive) {
            user.setName(req.getName());
            hasChanged = true;
        }
        if (hasChanged) {
            user.save();
        }
        req.setPassword(req.getNewPassword());
        req.setNewPassword("");
        return req;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public UserObject getUser(@RequestParam("sessionId") String sessionId, @RequestParam("userId") int userId) {

        EntityFactory entityFactory = new EntityFactory();
        User user = entityFactory.getModelObject(userId, User.class);
        if (user.getSessionId() == null || !user.getSessionId().equals(sessionId)) {
            throw new NotLoggedInException("No active session for user");
        }
        return user.createUserObject();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/getGroups", method = RequestMethod.GET)
    public GroupsResponse getGroups(@RequestParam("sessionId") String sessionId, @RequestParam("userId") int userId) {
        EntityFactory entityFactory = new EntityFactory();
        User user = entityFactory.getModelObject(userId, User.class);
        if (user == null) {
            throw new EntityNotFoundException("No user found for id or sessionId");
        }
        if (user.getSessionId() == null || !user.getSessionId().equals(sessionId)) {
            throw new NotLoggedInException("No active session for user");
        }
        HashMap<String, List<GroupObject>> groupsMap = new HashMap<>();
        List<GroupObject> groupObjects = new ArrayList<>();

        for (Group group : user.getGroups()) {
            List<FriendObject> includedFriends = new ArrayList<>();
            for (User groupMember : group.getMembers()) {

                boolean notMe = user.equals(groupMember);
                boolean hasValidLocation = groupMember.getLocation() != null && !groupMember.getLocation().equals("");

                if (!notMe && hasValidLocation) {
                    Location location = gson.fromJson(user.getLocation(), Location.class);
                    includedFriends.add(new FriendObject(user.getId(), user.getName(), location));
                }
            }
            GroupObject groupObject = new GroupObject(group.getId(), group.getName(), includedFriends);
            groupObjects.add(groupObject);
        }
        groupsMap.put("groups", groupObjects);
        return new GroupsResponse(groupsMap);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/getFriends", method = RequestMethod.GET)
    public UsersResponse getFriends(@RequestParam("sessionId") String sessionId, @RequestParam("userId") int userId) {
        EntityFactory entityFactory = new EntityFactory();
        User user = entityFactory.getModelObject(userId, User.class);
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
        return new UsersResponse(new EntityFactory().getAllUsersAsPublicUserObjects());
    }

}
