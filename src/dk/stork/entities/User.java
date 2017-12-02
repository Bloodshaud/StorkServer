package dk.stork.entities;

import dk.stork.requestHandling.communicationObjects.UserObject;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public UserObject createUserObject() {
        return new UserObject(id, name, mail, sessionId);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        return id == ((User) obj).getId();
    }
}
