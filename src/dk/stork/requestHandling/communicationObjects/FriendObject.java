package dk.stork.requestHandling.communicationObjects;

/**
 * @author morten
 */
public class FriendObject {
    private int id;
    private String name;
    private Location location;

    public FriendObject(int userId, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
