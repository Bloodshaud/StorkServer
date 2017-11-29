package dk.stork.requestHandling.controllers;

import dk.stork.entities.EntityFactory;
import dk.stork.entities.User;
import dk.stork.exceptions.EntityNotFoundException;
import dk.stork.requestHandling.requestObjects.Login;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Johannes Ernstsen
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Login login(@RequestParam(value = "mail") String mail, @RequestParam("password") String password) {
        Login login = new Login();
        login.setSuccess(false);
        User user = null;
        try {
            user = EntityFactory.getUserFromEmail(mail);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());//TODO: REPLACE WITH LOGGER
        }

        if (user != null) {
            if (user.getPassword().equals(password)) {
                String sessionId = UUID.randomUUID().toString();
                login.setSuccess(true);
                login.setSessionId(sessionId);
                user.setSessionId(sessionId);
                user.save();
            }
        }

        EntityFactory.destroy();
        return login;
    }
}
