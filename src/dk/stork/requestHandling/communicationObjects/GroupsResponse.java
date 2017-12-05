package dk.stork.requestHandling.communicationObjects;

import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author johannes, morten
 */
public class GroupsResponse {
    private List<GroupObject> groups;

    public GroupsResponse() {
    }

    public GroupsResponse(List<GroupObject> groups) {
        this.groups = groups;
    }

    public static void main(String[] args) {
        /*HashMap<String, Location> stringLocationHashMap = new HashMap<>();
        stringLocationHashMap.put("Morten", new Location(1.7, 2.3, new Date().getTime()));
        stringLocationHashMap.put("Søby", new Location(3.8, 4.5, new Date().getTime()));
        System.out.println(new Gson().toJson(new GroupsResponse(stringLocationHashMap)));*/
    }

    public List<GroupObject> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupObject> groups) {
        this.groups = groups;
    }
}

