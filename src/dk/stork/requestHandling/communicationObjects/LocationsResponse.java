package dk.stork.requestHandling.communicationObjects;

import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Johannes Ernstsen
 */
public class LocationsResponse {
    private Map<String, Location> locations;

    public LocationsResponse() {
    }

    public LocationsResponse(Map<String, Location> locations) {
        this.locations = locations;
    }

    public Map<String, Location> getLocations() {
        return locations;
    }

    public void setLocations(Map<String, Location> locations) {
        this.locations = locations;
    }

    public static void main(String[] args) {
        HashMap<String, Location> stringLocationHashMap = new HashMap<>();
        stringLocationHashMap.put("Morten", new Location(1.7, 2.3, new Date().getTime()));
        stringLocationHashMap.put("Søby", new Location(3.8, 4.5, new Date().getTime()));
        System.out.println(new Gson().toJson(new LocationsResponse(stringLocationHashMap)));
    }
}

