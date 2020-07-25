import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class Location {

    private HashMap<String, Location> connections = new HashMap<>();
    private String name;
    private String description = "in an unknown area";
    private ArrayList<NPCharacter> NPCharacters = new ArrayList<>();
    private HashMap<String, Item> items = new HashMap<>();
    private Color mapColor;
    private String type;

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

    public Color getMapColor() {
        return mapColor;
    }

    public void setMapColor(Color mapColor) {
        this.mapColor = mapColor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void addItem(Item item){
        items.put(item.getType(), item);
    }

    public void removeItem(Item item){
        items.remove(item.getType(), item);
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

    public String displayItems(){
        String item = "";
        if(items.size() == 0) return "There are no items here";
        if(items.size() == 1) item = "There is 1 item here";
        else item = "There are " + items.size() + " items here";
        if(items.size() == 1) item += ". It is a ";
        else item += "They are: ";
        int remaining = items.size();
        for (Item it: items.values()) {
            item += it.getType();
            remaining--;
            if(remaining != 0) item += ", ";
        }
        return item;
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
        newDesc += "\n" + displayItems();
        return newDesc;
    }
}
