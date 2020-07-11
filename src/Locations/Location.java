import java.util.ArrayList;
import java.util.HashMap;

public class Location {

    private HashMap<String, Location> connections = new HashMap<>();
    private String name;
    private String description = "in an unknown area";
    private ArrayList<NPCharacter> NPCharacters = new ArrayList<>();

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

    public ArrayList<NPCharacter> getNPCharacters() {
        return NPCharacters;
    }

    public void addCharacter(NPCharacter NPCharacter){
        NPCharacters.add(NPCharacter);
    }

    public void removeCharacter(NPCharacter NPCharacter){
        NPCharacters.remove(NPCharacter);
    }

    public String displayCharacters(){
        String chars = "";
        if(NPCharacters.size() == 0) return "There are no people here";
        if(NPCharacters.size() == 1) chars = "There is 1 person here";
        else chars = "There are " + NPCharacters.size() + " people here";
        if(NPCharacters.size() == 1) chars += ". Their name is ";
        else chars += "Their names are ";
        int remaining = NPCharacters.size();
        for (NPCharacter ch: NPCharacters) {
            chars += ch.getName();
            remaining--;
            if(remaining != 0) chars += ", ";
        }
        return chars;
    }

    @Override
    public String toString() {
        String newDesc = description;
        if(connections.size() == 0) newDesc += ". There are no paths";
        else if (connections.size() == 1){
            newDesc += ". There is 1 path to the ";
            for (String key: connections.keySet()) {
                newDesc += key;
            }
        }
        else {
            newDesc += ". There are " + connections.size() + " paths";
            for (String key : connections.keySet()) {
                newDesc += ", One to the " + key;
            }
        }
        newDesc += "\n" + displayCharacters();
        return newDesc;
    }
}
