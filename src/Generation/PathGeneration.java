import java.awt.*;

public class PathGeneration {

    private static int pathSide = 5;//this sets the size of the locations perimeter
    private static int types = 1;

    public static Location[][] generatePath(WorldLocation worldLocation){
        Location[][] pathLocs = new Location[pathSide][pathSide];
        int type = Controller.randomNumber(0, types);
        setColor(type, worldLocation);
        for (int i = 0; i < pathSide; i++) {
            for (int j = 0; j < pathSide; j++) {
                pathLocs[i][j] = getType(type);
            }
        }
        for (int i = 0; i < pathSide; i++) {
            for (int j = 0; j < pathSide; j++) {
                setConnections(pathLocs, i, j);
            }
        }
        /*Main.setStartLocation(pathLocs[0][0]);
        Main.setMapDisplay(new MapDisplay(Main.getPlayer(), 50, pathLocs, 0, 0));*/
        return pathLocs;
    }

    private static Path getType(int type){
        switch (type){
            default:
                return new ForestPath();
        }
    }

    private static void setColor(int type, WorldLocation worldLocation){
        switch (type){
            default:
                worldLocation.setMapColor(new Color(0, 100, 0));
                break;
        }
    }

    private static void setConnections(Location[][] paths, int x, int y){
        Location path = paths[x][y];
        if((x+1) < paths.length) {
            Location rPath = paths[x+1][y];
            path.addConnection(rPath, "east");
            rPath.addConnection(path, "west");
        }
        if((y+1) < paths.length){
            Location dPath = paths[x][y+1];
            path.addConnection(dPath, "south");
            dPath.addConnection(path, "north");
        }
    }

}
