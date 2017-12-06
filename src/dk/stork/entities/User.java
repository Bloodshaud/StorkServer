package dk.stork.entities;

import dk.stork.requestHandling.communicationObjects.PublicUserObject;
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

    @SuppressWarnings("unused")
    public User() {
    }

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
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
    public String getPicture() {
        return picture;
    }

    @SuppressWarnings("unused")
    public void setPicture(String picture) {
        this.picture = picture;
    }

    @SuppressWarnings("unused")
    public String getMail() {
        return mail;
    }

    @SuppressWarnings("unused")
    public void setMail(String mail) {
        this.mail = mail;
    }

    @SuppressWarnings("unused")
    public String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }

    @SuppressWarnings("unused")
    public String getLocation() {
        return location;
    }

    @SuppressWarnings("unused")
    public void setLocation(String location) {
        this.location = location;
    }

    @SuppressWarnings("unused")
    public Set<Group> getGroups() {
        return groups;
    }

    @SuppressWarnings("unused")
    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @SuppressWarnings("unused")
    public String getSessionId() {
        return sessionId;
    }

    @SuppressWarnings("unused")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @SuppressWarnings("unused")
    public Set<User> getFriends() {
        return friends;
    }

    @SuppressWarnings("unused")
    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public UserObject createUserObject() {
        return new UserObject(id, name, mail, sessionId);
    }

    public PublicUserObject createFriendObject() {
        return new PublicUserObject(id, name, mail);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        return id == ((User) obj).getId();
    }
}
