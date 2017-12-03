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

    @SuppressWarnings("unused")
    public UserObject() {
    }

    @SuppressWarnings("unused")
    public UserObject(int userId, String name, String mail, String sessionId) {
        this.userId = userId;
        this.name = name;
        this.mail = mail;
        this.sessionId = sessionId;
    }

    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new UserObject(1, "Johannes Ernstsen", "Ernstsen.johannes@gmail.com", "e7d35d2d-9521-4aa9-a6c5-dc4b08aaf638")));
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
    public String getMail() {
        return mail;
    }

    @SuppressWarnings("unused")
    public void setMail(String mail) {
        this.mail = mail;
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
