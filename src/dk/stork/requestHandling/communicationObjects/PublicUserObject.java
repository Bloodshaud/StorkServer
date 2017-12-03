package dk.stork.requestHandling.communicationObjects;

/**
 * @author Johannes Ernstsen
 */
public class FriendObject {
    private int userId;
    private String name;
    private String mail;

    @SuppressWarnings("Unused")
    public FriendObject() {
    }

    @SuppressWarnings("Unused")
    public FriendObject(int userId, String name, String mail) {
        this.userId = userId;
        this.name = name;
        this.mail = mail;
    }

    @SuppressWarnings("Unused")
    public int getUserId() {
        return userId;
    }

    @SuppressWarnings("Unused")
    public void setUserId(int userId) {
        this.userId = userId;
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
    public String getMail() {
        return mail;
    }

    @SuppressWarnings("Unused")
    public void setMail(String mail) {
        this.mail = mail;
    }
}
