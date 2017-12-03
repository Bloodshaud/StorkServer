package dk.stork.requestHandling.communicationObjects;

import java.util.List;

/**
 * @author Johannes Ernstsen
 */
public class FriendsResponse {
    private List<FriendObject> friends;

    @SuppressWarnings("Unused")
    public FriendsResponse() {
    }

    @SuppressWarnings("Unused")
    public FriendsResponse(List<FriendObject> friends) {
        this.friends = friends;
    }

    @SuppressWarnings("Unused")
    public List<FriendObject> getFriends() {
        return friends;
    }

    @SuppressWarnings("Unused")
    public void setFriends(List<FriendObject> friends) {
        this.friends = friends;
    }
}
