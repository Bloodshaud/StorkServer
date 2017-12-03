package dk.stork.requestHandling.communicationObjects;

import java.io.Serializable;
import java.util.List;

/**
 * @author Johannes Ernstsen
 */
public class FriendChangeRequest implements Serializable {
    private ActionEnum action;
    private int userId;
    private String sessionId;
    private List<Integer> friends;

    @SuppressWarnings("Unused")
    public FriendChangeRequest() {
    }

    @SuppressWarnings("Unused")
    public FriendChangeRequest(ActionEnum action, int userId, String sessionId, List<Integer> friends) {
        this.action = action;
        this.userId = userId;
        this.sessionId = sessionId;
        this.friends = friends;
    }

    @SuppressWarnings("Unused")
    public ActionEnum getAction() {
        return action;
    }

    @SuppressWarnings("Unused")
    public void setAction(ActionEnum action) {
        this.action = action;
    }

    @SuppressWarnings("Unused")
    public int getUserId() {
        return userId;
    }

    @SuppressWarnings("Unused")
    public void setId(int userId) {
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

    @SuppressWarnings("Unused")
    public List<Integer> getFriends() {
        return friends;
    }

    @SuppressWarnings("Unused")
    public void setFriends(List<Integer> friends) {
        this.friends = friends;
    }

    public enum ActionEnum {
        ADD, REMOVE
    }
}
