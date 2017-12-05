package dk.stork.requestHandling.communicationObjects;

import java.util.List;

/**
 * @author morten
 */
public class GroupObject {
    private int id;
    private String name;
    private List<FriendObject> friends;

    public GroupObject(int id, String name, List<FriendObject> friends) {
        this.id = id;
        this.name = name;
        this.friends = friends;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<FriendObject> getFriends() {
        return friends;
    }
}
