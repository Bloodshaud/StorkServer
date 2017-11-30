package dk.stork.requestHandling.communicationObjects;

import com.google.gson.Gson;
import dk.stork.requestHandling.communicationObjects.HelperObjects.Location;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Johannes Ernstsen
 */
public class UpdateLocationRequest implements Serializable {

    private int userId;
    private String sessionId;
    private Location location;

    public UpdateLocationRequest() {
    }

    public UpdateLocationRequest(int userID, String sessionId, Location location) {
        this.userId = userID;
        this.sessionId = sessionId;
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        UpdateLocationRequest sess = new UpdateLocationRequest(1, "sess", new Location(1, 2, new Date().getTime()));
        System.out.println(gson.toJson(sess));
    }
}
