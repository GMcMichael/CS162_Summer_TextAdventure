import java.awt.Color;

public class ForestPath extends Path {

    public ForestPath(){
        this("Forest");
    }

    public ForestPath(String name) {
        super(name);
    }

    public ForestPath(String name, int moveDifficulty, int npcChance) {
        super(name, moveDifficulty, npcChance);
    }

    @Override
    protected void setInfo(){
        setDescription("in a forest surrounded by trees");
        setMapColor(new Color(10, Controller.randomNumber(50, 150), 25));
        setType("forest");
    }


}
