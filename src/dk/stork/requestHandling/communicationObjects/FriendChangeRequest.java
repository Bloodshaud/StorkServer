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

    @SuppressWarnings("unused")
    public FriendChangeRequest() {
    }

    @SuppressWarnings("unused")
    public FriendChangeRequest(ActionEnum action, int userId, String sessionId, List<Integer> friends) {
        this.action = action;
        this.userId = userId;
        this.sessionId = sessionId;
        this.friends = friends;
    }

    @SuppressWarnings("unused")
    public ActionEnum getAction() {
        return action;
    }

    @SuppressWarnings("unused")
    public void setAction(ActionEnum action) {
        this.action = action;
    }

    @SuppressWarnings("unused")
    public int getUserId() {
        return userId;
    }

    @SuppressWarnings("unused")
    public void setId(int userId) {
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

    @SuppressWarnings("unused")
    public List<Integer> getFriends() {
        return friends;
    }

    @SuppressWarnings("unused")
    public void setFriends(List<Integer> friends) {
        this.friends = friends;
    }

    public enum ActionEnum {
        ADD, REMOVE
    }
}
