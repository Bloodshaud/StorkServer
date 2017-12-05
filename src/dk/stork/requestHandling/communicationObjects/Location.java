package dk.stork.requestHandling.communicationObjects;

import java.io.Serializable;

/**
 * @author Johannes Ernstsen
 */
public class Location implements Serializable {
    private double longitude;
    private double latitude;
    private long timeStamp;

    public Location() {
    }

    public Location(double longitude, double latitude, long timeStamp) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.timeStamp = timeStamp;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
