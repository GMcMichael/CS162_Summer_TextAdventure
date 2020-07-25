import java.awt.*;
import java.util.HashMap;

public class MazeLocation extends Location {

    private boolean mazeExit;
    private String exitDir;

    public MazeLocation(String name) {
        super(name);
        setMapColor(Color.gray);
    }

    public void setMazeExit(String exitDir, MazeLocation location){
        this.mazeExit = true;
        this.exitDir = exitDir;
        addConnection(location, exitDir);
        location.addConnection(this, "hatch");
        location.setMazeExit(true);
    }

    public void setMazeExit(boolean mazeExit){
        this.mazeExit = mazeExit;
    }

    public boolean getMazeExit(){
        return mazeExit;
    }

    public String getExitDir(){
        return exitDir;
    }

    @Override
    public String toString() {
        String newDesc = getDescription();
        HashMap<String, Location> connections = getConnections();
        if(connections.size() == 0) newDesc += ". There are no passageways";
        else if (connections.size() == 1){
            if(!getMazeExit()) {
                newDesc += ". There is 1 passageway to the ";
                for (String key : connections.keySet()) {
                    newDesc += key;
                }
            }
        }
        else {
            newDesc += ". There are " + connections.size() + " passageways";
            for (String key : connections.keySet()) {
                if(!key.equalsIgnoreCase("hatch")) newDesc += ", One to the " + key;
            }
        }
        if(getMazeExit()){
            if(connections.size() != 1) newDesc += ", and a hatch. The exit seems to be the " + exitDir;
            else newDesc += ". There seems to an exit through a hatch";
        }
        return newDesc;
    }

}
