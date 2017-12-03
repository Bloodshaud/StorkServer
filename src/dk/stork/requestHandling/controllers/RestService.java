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
        LoginRequest loginRequest = gson.fromJson(loginString, LoginRequest.class);
        loginRequest.setSuccess(false);

        User user = EntityFactory.getUserFromEmail(loginRequest.getMail());

        if (user != null) {
            if (user.getPassword().equalsIgnoreCase(loginRequest.getPassword())) {
                loginRequest.setSuccess(true);
                String sessionId = EntityFactory.login(user);
                loginRequest.setSessionId(sessionId);
            } else {
                loginRequest.setSuccess(false);
            }
        } else {
            throw new EntityNotFoundException("No user with supplied mail");
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
        switch (req.getAction()) {
            case ADD:
                user.addFriends(friends);
            case REMOVE:
                user.removeFriends(friends);
            default:
                throw new RuntimeException("Malformed Body - no action found");
        }
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
    @RequestMapping(value = "/getLocations", method = RequestMethod.GET)
    public LocationsResponse getLocations(@RequestParam("sessionId") String sessionId, @RequestParam("userId") int userId) {
        User user = EntityFactory.getModelObject(userId, User.class);
        if (user == null) {
            throw new EntityNotFoundException("No user found for id or sessionId");
        }
        if (user.getSessionId() == null || !user.getSessionId().equals(sessionId)) {
            throw new NotLoggedInException("No active session for user");
        }
        HashMap<String, Location> locations = new HashMap<>();
        HashSet<User> includedFriends = new HashSet<>();

        for (Group group : user.getGroups()) {
            for (User friend : group.getMembers()) {
                if (!user.equals(friend) && !includedFriends.contains(friend)) {
                    includedFriends.add(friend);
                    if (friend.getLocation() != null && !friend.getLocation().equals("")) {
                        locations.put(friend.getName(), gson.fromJson(friend.getLocation(), Location.class));
                    }
                }
            }
        } //TODO: IMPLEMENT ACTIVATED BOOLEAN FOR GROUP??
        return new LocationsResponse(locations);
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
