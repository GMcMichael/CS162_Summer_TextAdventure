public class MazeGeneration {

    private static String mazeExitDir;
    private static Location mazeExit;

    public static Location[][] generateMaze(int mazeRows, int mazeCols){//todo make to so that you need to find a key or something to open the exit
        Location[][] dirs = new Location[mazeRows][mazeCols];
        int remaining = (mazeRows * mazeCols) - 1;
        mazeRows--;
        mazeCols--;
        int x = Controller.randomNumber(0, mazeCols);
        int y = Controller.randomNumber(0, mazeRows);
        while (remaining > 0){
            int[] nCoords = getNewCoords(x, y, mazeCols, mazeRows);
            if(dirs[nCoords[0]][nCoords[1]] == null){
                if(dirs[x][y] == null){
                    dirs[x][y] = new Location("Maze Room");
                    dirs[x][y].setDescription("in a dark stone hallway");
                }
                dirs[nCoords[0]][nCoords[1]] = new Location("Maze Room");
                dirs[nCoords[0]][nCoords[1]].addConnection(dirs[x][y], Character.toString((char) nCoords[3]));
                dirs[nCoords[0]][nCoords[1]].setDescription("in a dark stone hallway");
                dirs[x][y].addConnection(dirs[nCoords[0]][nCoords[1]], Character.toString((char) nCoords[2]));
                remaining--;
            }
            x = nCoords[0];
            y = nCoords[1];
        }
        Location startLocation;
        int side = Controller.randomNumber(0, 4);
        int sX = 0;
        int sY = 0;
        String dir;
        switch (side){
            case 0:
                dir = "north";
                mazeExitDir = "south";
                sX = Controller.randomNumber(0, mazeCols);
                break;
            case 1:
                dir = "east";
                mazeExitDir = "west";
                sX = mazeCols;
                sY = Controller.randomNumber(0, mazeRows);
                break;
            case 2:
                dir = "south";
                mazeExitDir = "north";
                sX = Controller.randomNumber(0, mazeCols);
                sY = mazeRows;
                break;
            default:
                dir = "west";
                mazeExitDir = "east";
                sY = Controller.randomNumber(0, mazeRows);
                break;
        }
        startLocation = dirs[sX][sY];
        Main.setMazeExitDir(mazeExitDir);
        Main.setStartLocation(startLocation, dir);//todo this sets start to exit, change to random
        Main.setMapDisplay(new MapDisplay(Main.getPlayer(), 50, dirs, sX, sY));//todo maybe make the exit not drawn at all, right now it is only drawn if it is on the right or bottom edge. also maybe make the exit in the maze with a hatch instead of an extra room.
        return dirs;
    }

    private static int[] getNewCoords(int x, int y, int xMax, int yMax){
        int dir = Controller.randomNumber(0, 4);
        int[] nCoords = new int[4];
        switch (dir){
            case 0:
                nCoords[0] = x;
                nCoords[1] = y - 1;
                nCoords[2] = 110;
                nCoords[3] = 115;
                break;
            case 1:
                nCoords[0] = x + 1;
                nCoords[1] = y;
                nCoords[2] = 101;
                nCoords[3] = 119;
                break;
            case 2:
                nCoords[0] = x;
                nCoords[1] = y + 1;
                nCoords[2] = 115;
                nCoords[3] = 110;
                break;
            default:
                nCoords[0] = x - 1;
                nCoords[1] = y;
                nCoords[2] = 119;
                nCoords[3] = 101;
                break;
        }
        if(nCoords[0] > xMax || nCoords[0] < 0 || nCoords[1] > yMax || nCoords[1] < 0) return getNewCoords(x, y, xMax, yMax);
        return nCoords;
    }

}
