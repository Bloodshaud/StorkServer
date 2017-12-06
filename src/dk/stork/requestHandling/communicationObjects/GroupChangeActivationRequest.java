package dk.stork.requestHandling.communicationObjects;

import java.util.List;

/**
 * @author Johannes Ernstsen
 */
public class GroupChangeActivationRequest {
    private int userId;
    private String sessionId;
    private List<Integer> add;
    private List<Integer> remove;

    @SuppressWarnings("unused")
    public GroupChangeActivationRequest() {
    }

    @SuppressWarnings("unused")
    public GroupChangeActivationRequest(int userId, String sessionId, List<Integer> add, List<Integer> remove) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.add = add;
        this.remove = remove;
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

    @SuppressWarnings("unused")
    public List<Integer> getAdd() {
        return add;
    }

    @SuppressWarnings("unused")
    public void setAdd(List<Integer> add) {
        this.add = add;
    }

    @SuppressWarnings("unused")
    public List<Integer> getRemove() {
        return remove;
    }

    @SuppressWarnings("unused")
    public void setRemove(List<Integer> remove) {
        this.remove = remove;
    }
}
