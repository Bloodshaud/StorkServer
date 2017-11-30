package dk.stork.requestHandling.controllers;

import com.google.gson.Gson;
import dk.stork.entities.EntityFactory;
import dk.stork.entities.User;
import dk.stork.requestHandling.RegisterUserRequest;
import dk.stork.requestHandling.communicationObjects.LoginRequest;
import dk.stork.requestHandling.communicationObjects.LogoutRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
            }
        }

        EntityFactory.destroy();
        return loginRequest;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public LogoutRequest logout(@RequestBody String logoutString) {
        LogoutRequest logoutRequest = gson.fromJson(logoutString, LogoutRequest.class);
        User user = EntityFactory.getModelObject(logoutRequest.getUserId(), User.class);
        if (user == null) {
            user = EntityFactory.getUserFromSessionId(logoutRequest.getSessionId());
        }
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

        req.setPassword("");
        req.setSessionId(sessionId);

        return req;
    }
}
