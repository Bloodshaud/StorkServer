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

    @SuppressWarnings("Unused")
    public RegisterUserRequest() {
    }

    @SuppressWarnings("Unused")
    public RegisterUserRequest(boolean success, String name, String password, String mail, int userId, String sessionId) {
        this.success = success;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.userId = userId;
        this.sessionId = sessionId;
    }

    @SuppressWarnings("Unused")
    public boolean isSuccess() {
        return success;
    }

    @SuppressWarnings("Unused")
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @SuppressWarnings("Unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("Unused")
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("Unused")
    public String getPassword() {
        return password;
    }

    @SuppressWarnings("Unused")
    public void setPassword(String password) {
        this.password = password;
    }

    @SuppressWarnings("Unused")
    public String getMail() {
        return mail;
    }

    @SuppressWarnings("Unused")
    public void setMail(String mail) {
        this.mail = mail;
    }

    @SuppressWarnings("Unused")
    public int getUserId() {
        return userId;
    }

    @SuppressWarnings("Unused")
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @SuppressWarnings("Unused")
    public String getSessionId() {
        return sessionId;
    }

    @SuppressWarnings("Unused")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
