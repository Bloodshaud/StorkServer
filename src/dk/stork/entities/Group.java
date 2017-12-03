package dk.stork.entities;

import java.util.Set;

/**
 * @author Johannes Ernstsen
 */
public class Group extends EntityObject {
    private int id;
    private String name;
    private Set<User> members;

    @SuppressWarnings("Unused")
    public Group() {
    }

    @SuppressWarnings("Unused")
    public Group(int id, String name, Set<User> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    @SuppressWarnings("Unused")
    public int getId() {
        return id;
    }

    @SuppressWarnings("Unused")
    public void setId(int id) {
        this.id = id;
    }

    @SuppressWarnings("Unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("Unused")
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("Unused")
    public Set<User> getMembers() {
        return members;
    }

    @SuppressWarnings("Unused")
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
