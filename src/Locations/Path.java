import java.awt.Color;

public abstract class Path extends Location {

    private int moveDifficulty = 1;
    private int npcChance = 10;

    public Path(String name) {
        super(name);
        setInfo();
    }

    public Path(String name, int moveDifficulty, int npcChance) {
        super(name);
        this.moveDifficulty = moveDifficulty;
        this.npcChance = npcChance;
        setInfo();
    }

    public int getMoveDifficulty() {
        return moveDifficulty;
    }

    public int getNpcChance() {
        return npcChance;
    }

    protected void setInfo(){
        setMapColor(Color.black);
        setType("default");
    }
}
