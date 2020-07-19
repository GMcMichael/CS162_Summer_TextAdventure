public class WorldMapGeneration {

    private static int types = 2;

    public static WorldLocation[][] generateWorld(int rows, int cols){
        return generateWorld(rows, cols, -1);
    }

    public static WorldLocation[][] generateWorld(int rows, int cols, int towns){
        //todo generate random towns with paths and different locations (forests, rivers, mountains, ruins) between them
        int spaces = rows*cols;
        if(towns == -1) towns = spaces/5;
        if(towns == 0) towns = 1;
        WorldLocation[][] world = new WorldLocation[cols][rows];
        int[][] townCoords = new int[towns][2];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                world[i][j] = generateLocation();
            }
        }
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                setConnections(world, i, j);
            }
        }
        WorldLocation sWLoc = world[0][0];//todo update world starting position (maybe always to a town)
        Location[][] sLocs = sWLoc.getAreaLocations();
        Main.setStartWorldLocation(sWLoc);
        Main.setStartLocation(sLocs[0][0]);
        Main.setOverworldMapDisplay(new OverworldMapDisplay(Main.getPlayer(), 50, world, 0, 0));
        Main.setMapDisplay(new MapDisplay(Main.getPlayer(), 50, sLocs, 0, 0));
        return world;
    }

    private static WorldLocation generateLocation(){
        WorldLocation worldLocation = new WorldLocation();
        switch (Controller.randomNumber(0, types)) {
            case 0:
                worldLocation.generateTown();
                break;
            case 1:
                worldLocation.generatePath();
                break;
        }
        return worldLocation;
    }

    private static void setConnections(WorldLocation[][] world, int x, int y){
        WorldLocation worldLoc = world[x][y];
        if((x+1) < world.length) {
            WorldLocation rWorldLoc = world[x+1][y];
            worldLoc.addConnection(rWorldLoc, "east");
            rWorldLoc.addConnection(worldLoc, "west");
        }
        if((y+1) < world.length){
            WorldLocation dWorldLoc = world[x][y+1];
            worldLoc.addConnection(dWorldLoc, "south");
            dWorldLoc.addConnection(worldLoc, "north");
        }
    }

}
