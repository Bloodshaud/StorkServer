package dk.stork.requestHandling.communicationObjects;

import java.util.List;

/**
 * @author morten, johannes
 */
public class GroupObject {
    private int id;
    private String name;
    private List<FriendObject> friends;
    private int owner;

    public GroupObject(int id, String name, List<FriendObject> friends, int owner) {
        this.id = id;
        this.name = name;
        this.friends = friends;
        this.owner = owner;
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

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
