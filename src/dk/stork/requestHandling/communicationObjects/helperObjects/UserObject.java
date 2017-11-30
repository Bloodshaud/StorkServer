package dk.stork.requestHandling.communicationObjects.HelperObjects;

import com.google.gson.Gson;

/**
 * @author Johannes Ernstsen
 */
public class UserObject {
    private int id;
    private String name;
    private String mail;
    private String sessionId;

    public UserObject() {
    }

    public UserObject(int id, String name, String mail, String sessionId) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new UserObject(1, "Johannes Ernstsen", "Ernstsen.johannes@gmail.com", "e7d35d2d-9521-4aa9-a6c5-dc4b08aaf638")));
    }
}
