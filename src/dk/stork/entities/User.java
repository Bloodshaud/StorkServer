package dk.stork.entities;

import dk.stork.requestHandling.communicationObjects.FriendObject;
import dk.stork.requestHandling.communicationObjects.UserObject;

import java.util.List;
import java.util.Set;

/**
 * @author Johannes
 * <p>
 * Modelclass (DAO type class) for user table from database - don't be an idiot about it.
 * <p>
 * If problems arise ask author(s) - hibernate is very strict
 */
public class User extends EntityObject {
    private int id;
    private String name;
    private String picture;
    private String mail;
    private String password;
    private String location;
    private Set<Group> groups;
    private Set<User> friends;
    private String sessionId;

    @SuppressWarnings("Unused")
    public User() {
    }

    @SuppressWarnings("Unused")
    public User(int id, String picture, String mail, String password, String location, Set<Group> groups, Set<User> friends, String sessionId) {
        this.id = id;
        this.picture = picture;
        this.mail = mail;
        this.password = password;
        this.location = location;
        this.groups = groups;
        this.friends = friends;
        this.sessionId = sessionId;
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
    public int getId() {
        return id;
    }

    @SuppressWarnings("Unused")
    public void setId(int id) {
        this.id = id;
    }

    @SuppressWarnings("Unused")
    public String getPicture() {
        return picture;
    }

    @SuppressWarnings("Unused")
    public void setPicture(String picture) {
        this.picture = picture;
    }

    @SuppressWarnings("Unused")
    public String getMail() {
        return mail;
    }

    @SuppressWarnings("Unused")
    public void setMail(String mail) {
        this.mail = mail;
    }

    @SuppressWarnings("Unused")
    public String getPassword() {
        return password;
    }

    @SuppressWarnings("Unused")
    public void setPassword(String password) {
        this.password = password;
    }

    @SuppressWarnings("Unused")
    public String getLocation() {
        return location;
    }

    @SuppressWarnings("Unused")
    public void setLocation(String location) {
        this.location = location;
    }

    @SuppressWarnings("Unused")
    public Set<Group> getGroups() {
        return groups;
    }

    @SuppressWarnings("Unused")
    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @SuppressWarnings("Unused")
    public String getSessionId() {
        return sessionId;
    }

    @SuppressWarnings("Unused")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @SuppressWarnings("Unused")
    public Set<User> getFriends() {
        return friends;
    }

    @SuppressWarnings("Unused")
    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @SuppressWarnings("Unused")
    public void removeFriends(List<User> users) {
        friends.removeAll(users);
    }

    @SuppressWarnings("Unused")
    public void addFriends(List<User> users) {
        friends.addAll(users);
    }

    public UserObject createUserObject() {
        return new UserObject(id, name, mail, sessionId);
    }

    public FriendObject createFriendObject() {
        return new FriendObject(id, name, mail);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        return id == ((User) obj).getId();
    }
}
