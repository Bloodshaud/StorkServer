package dk.stork.entities;

import java.util.Set;

/**
 * @author Johannes Ernstsen
 */
public class Group extends EntityObject {
    private int id;
    private String name;
    private Set<User> members;

    @SuppressWarnings("unused")
    public Group() {
    }

    @SuppressWarnings("unused")
    public Group(int id, String name, Set<User> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    @SuppressWarnings("unused")
    public int getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public void setId(int id) {
        this.id = id;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("unused")
    public Set<User> getMembers() {
        return members;
    }

    @SuppressWarnings("unused")
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
