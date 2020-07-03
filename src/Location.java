import java.util.HashMap;

public class Location {

    private HashMap<String, Location> connections = new HashMap<>();
    private String name;
    private String description = "in an unknown area";
    private boolean mazeExit;
    private String exitDir;

    public Location(String name){
        setName(name);
    }

    public void addConnection(Location location, String dir){
        if(dir.toLowerCase().equals("n")) dir = "north";
        else if(dir.toLowerCase().equals("s")) dir = "south";
        else if(dir.toLowerCase().equals("e")) dir = "east";
        else if(dir.toLowerCase().equals("w")) dir = "west";
        connections.put(dir.toLowerCase(), location);
    }

    public Location getConnection(String key){
        return connections.get(key);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMazeExit(boolean mazeExit, String exitDir, Location location){
        this.mazeExit = mazeExit;
        this.exitDir = exitDir;
        addConnection(location, exitDir);
        location.addConnection(this, Main.getMazeExitDir());
    }

    @Override
    public String toString() {
        String newDesc = description;
        if(connections.size() == 0) newDesc += ". There are no passageways";
        else if (connections.size() == 1){
            newDesc += ". There is 1 passageway to the ";
            for (String key: connections.keySet()) {
                newDesc += key;
            }
        }
        else {
            newDesc += ". There are " + connections.size() + " passageways";
            for (String key : connections.keySet()) {
                newDesc += ", One to the " + key;
            }
        }
        if(mazeExit) newDesc += ". The exit seems to be to the " + exitDir;
        return newDesc;
    }
}
