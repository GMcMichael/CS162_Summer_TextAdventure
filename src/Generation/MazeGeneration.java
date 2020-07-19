public class MazeGeneration {

    public static Location[][] generateMaze(int mazeRows, int mazeCols){//maybe add the need for a key to use the exit
        MazeLocation[][] dirs = new MazeLocation[mazeRows][mazeCols];
        int remaining = (mazeRows * mazeCols) - 1;
        mazeRows--;
        mazeCols--;
        int x = Controller.randomNumber(0, mazeCols);
        int y = Controller.randomNumber(0, mazeRows);
        while (remaining > 0){
            int[] nCoords = getNewCoords(x, y, mazeCols, mazeRows);
            if(dirs[nCoords[0]][nCoords[1]] == null){
                if(dirs[x][y] == null){
                    dirs[x][y] = new MazeLocation("Maze Room");
                    dirs[x][y].setDescription("in a dark stone hallway");
                }
                dirs[nCoords[0]][nCoords[1]] = new MazeLocation("Maze Room");
                dirs[nCoords[0]][nCoords[1]].addConnection(dirs[x][y], Character.toString((char) nCoords[3]));
                dirs[nCoords[0]][nCoords[1]].setDescription("in a dark stone hallway");
                dirs[x][y].addConnection(dirs[nCoords[0]][nCoords[1]], Character.toString((char) nCoords[2]));
                remaining--;
            }
            x = nCoords[0];
            y = nCoords[1];
        }
        MazeLocation startLocation;
        int nX = Controller.randomNumber(0, mazeCols);
        int nY = Controller.randomNumber(0, mazeRows);
        dirs[nX][nY].setMazeExit("hatch", new MazeLocation("Maze Exit"));
        nX = Controller.randomNumber(0, mazeCols);
        nY = Controller.randomNumber(0, mazeRows);
        startLocation = dirs[nX][nY];
        /*Main.setStartLocation(startLocation);
        Main.setMapDisplay(new MapDisplay(Main.getPlayer(), 50, dirs, nX, nY));*///the map is created here
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
