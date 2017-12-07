package dk.stork.requestHandling.communicationObjects;

import dk.stork.entities.Group;

import java.util.List;

/**
 * @author Johannes Ernstsen
 */
public class UserObject {
    private int userId;
    private String name;
    private String mail;
    private String sessionId;
    private List<Integer> activeGroups;

    @SuppressWarnings("unused")
    public UserObject() {
    }

    @SuppressWarnings("unused")
    public UserObject(int userId, String name, String mail, String sessionId, List<Integer> activeGroups) {
        this.userId = userId;
        this.name = name;
        this.mail = mail;
        this.sessionId = sessionId;
        this.activeGroups = activeGroups;
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

    public List<Integer> getActiveGroups() {
        return activeGroups;
    }

    public void setActiveGroups(List<Integer> activeGroups) {
        this.activeGroups = activeGroups;
    }
}
