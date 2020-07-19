public class ForestPath extends Path {

    private String baseDesc = "in a forest surrounded by trees";

    public ForestPath(){
        this("Forest");
    }

    public ForestPath(String name) {
        super(name);
        setDescription(baseDesc);
    }

    public ForestPath(String name, int moveDifficulty, int npcChance) {
        super(name, moveDifficulty, npcChance);
        setDescription(baseDesc);
    }
}
