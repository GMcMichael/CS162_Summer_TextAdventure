import java.awt.Color;
import java.util.HashMap;

public class WorldLocation {

    private HashMap<String, WorldLocation> connections = new HashMap<>();
    private HashMap<String, Integer> connectionsDifficulty = new HashMap<>();
    private Location[][] areaLocations;
    private String type;
    private String name;
    private System desc;
    private Color mapColor;

    public void generateTown(){
        type = "town";
        areaLocations = TownGeneration.generateTown();
        mapColor = new Color(140, 115, 10);
    }

    public void generateMaze(){
        type = "maze";
        areaLocations = MazeGeneration.generateMaze(5, 5);
        mapColor = new Color(100, 100, 100);
    }

    public void generatePath(){
        type = "path";
        areaLocations = PathGeneration.generatePath(this);
    }

    public WorldLocation getConnection(String key){
        return connections.get(key);
    }

    public int getDifficulty(String key){
        return connectionsDifficulty.get(key);
    }

    public void addConnection(WorldLocation location, String dir){
        connections.put(dir.toLowerCase(), location);
    }

    public HashMap<String, WorldLocation> getConnections() {
        return connections;
    }

    public HashMap<String, Integer> getConnectionsDifficulty() {
        return connectionsDifficulty;
    }

    public Location[][] getAreaLocations() {
        return areaLocations;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public System getDesc() {
        return desc;
    }

    public void setDesc(System desc) {
        this.desc = desc;
    }

    public Color getMapColor() {
        return mapColor;
    }

    public void setMapColor(Color mapColor) {
        this.mapColor = mapColor;
    }

    public boolean isTown(){
        return type.equalsIgnoreCase("town");
    }
}
