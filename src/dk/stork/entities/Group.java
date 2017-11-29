package dk.stork.entities;

import java.util.Set;

/**
 * @author Johannes Ernstsen
 */
public class Group extends EntityObject {
    private int id;
    private String name;
    private Set<User> members;

    public Group() {
    }

    public Group(int id, String name, Set<User> members) {
        this.id = id;
        this.name = name;
        this.members = members;
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

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : getMembers()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(user.getName());
        }
        return name + ": " + stringBuilder.toString();
    }
}
