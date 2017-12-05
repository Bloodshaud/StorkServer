package dk.stork.requestHandling.communicationObjects;

import com.google.gson.Gson;

/**
 * @author Johannes Ernstsen
 */
public class ChangeUserRequest {
    private String sessionId;
    private int userId;
    private String name;
    private String password;
    private String newPassword;

    @SuppressWarnings("unused")
    public ChangeUserRequest() {
    }

    @SuppressWarnings("unused")
    public ChangeUserRequest(String sessionId, int userId, String name, String password, String newPassword) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.newPassword = newPassword;
    }

    @SuppressWarnings("unused")
    public String getSessionId() {
        return sessionId;
    }

    @SuppressWarnings("unused")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
    public String getNewPassword() {
        return newPassword;
    }

    @SuppressWarnings("unused")
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new ChangeUserRequest("Sesh", 2, "Morten", "Kode", "nyKode")));
    }
}
