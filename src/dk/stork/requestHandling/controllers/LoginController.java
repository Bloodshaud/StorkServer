package dk.stork.requestHandling.controllers;

import com.google.gson.Gson;
import dk.stork.entities.EntityFactory;
import dk.stork.entities.User;
import dk.stork.exceptions.EntityNotFoundException;
import dk.stork.requestHandling.communicationObjects.LoginRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Johannes Ernstsen
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginRequest login(@RequestBody String loginString) {
        LoginRequest loginRequest = new Gson().fromJson(loginString, LoginRequest.class);
        loginRequest.setSuccess(false);
        User user = null;
        try {
            user = EntityFactory.getUserFromEmail(loginRequest.getMail());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());//TODO: REPLACE WITH LOGGER
        }
        if (user != null) {
            if (user.getPassword().equalsIgnoreCase(loginRequest.getPassword())) {
                String sessionId = UUID.randomUUID().toString();
                loginRequest.setSuccess(true);
                loginRequest.setSessionId(sessionId);
                user.setSessionId(sessionId);
                user.save();
            }
        }

        EntityFactory.destroy();
        return loginRequest;
    }
}
