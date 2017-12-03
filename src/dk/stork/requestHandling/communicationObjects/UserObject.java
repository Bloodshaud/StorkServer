package dk.stork.requestHandling.communicationObjects;

import com.google.gson.Gson;

/**
 * @author Johannes Ernstsen
 */
public class UserObject {
    private int userId;
    private String name;
    private String mail;
    private String sessionId;

    @SuppressWarnings("Unused")
    public UserObject() {
    }

    @SuppressWarnings("Unused")
    public UserObject(int userId, String name, String mail, String sessionId) {
        this.userId = userId;
        this.name = name;
        this.mail = mail;
        this.sessionId = sessionId;
    }

    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new UserObject(1, "Johannes Ernstsen", "Ernstsen.johannes@gmail.com", "e7d35d2d-9521-4aa9-a6c5-dc4b08aaf638")));
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
    public String getName() {
        return name;
    }

    @SuppressWarnings("Unused")
    public void setName(String name) {
        this.name = name;
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
    public String getSessionId() {
        return sessionId;
    }

    @SuppressWarnings("Unused")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
