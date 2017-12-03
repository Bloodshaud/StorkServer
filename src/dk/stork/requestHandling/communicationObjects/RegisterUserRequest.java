package dk.stork.requestHandling.communicationObjects;

import java.io.Serializable;

/**
 * @author Johannes Ernstsen
 */
public class RegisterUserRequest implements Serializable {
    private boolean success;
    private String name;
    private String password;
    private String mail;
    private int userId;
    private String sessionId;

    @SuppressWarnings("unused")
    public RegisterUserRequest() {
    }

    @SuppressWarnings("unused")
    public RegisterUserRequest(boolean success, String name, String password, String mail, int userId, String sessionId) {
        this.success = success;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.userId = userId;
        this.sessionId = sessionId;
    }

    @SuppressWarnings("unused")
    public boolean isSuccess() {
        return success;
    }

    @SuppressWarnings("unused")
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("unused")
    public String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }

    @SuppressWarnings("unused")
    public String getMail() {
        return mail;
    }

    @SuppressWarnings("unused")
    public void setMail(String mail) {
        this.mail = mail;
    }

    @SuppressWarnings("unused")
    public int getUserId() {
        return userId;
    }

    @SuppressWarnings("unused")
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @SuppressWarnings("unused")
    public String getSessionId() {
        return sessionId;
    }

    @SuppressWarnings("unused")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
