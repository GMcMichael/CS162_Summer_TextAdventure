import java.util.HashMap;

public class Location {

    private HashMap<String, Location> connections = new HashMap<>();
    private String name;
    private String key;
    private String description = "in an unknown area";

    public Location(String name, String key){
        setName(name);
        setKey(key);
    }

    public void addConnection(Location location, String dir){
        connections.put(dir.toLowerCase(), location);
    }

    public HashMap<String, Location> getConnections(){
        return connections;
    }

    public void setConnections(HashMap<String, Location> connections){
        this.connections = connections;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
