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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FriendObject> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendObject> friends) {
        this.friends = friends;
    }
}
