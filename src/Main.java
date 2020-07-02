import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static String name = "my text adventure";
    private static Scanner scanner;
    private static Player player;
    private static Controller controller;
    private static boolean gameRunning;
    private static ArrayList<Location[][]> RoomLists = new ArrayList<Location[][]>();
    private static Location[][] currLocation;

    public static void main(String[] args){
        //System.out.println(generateMaze(5, 5));
        System.out.println("Welcome to " + name + "!");
        scanner = new Scanner(System.in);
        Game();
    }

    private static void Game(){
        System.out.print("Would you like to start a game? \nYes or No? \n--- \n> ");
        char response = scanner.next().toLowerCase().charAt(0);
        if(response == 'n'){
            textBuffer();
            System.out.println("Okay, Goodbye!");
            System.exit(0);
        } else if(response == 'y') startGame();
        else {
            textBuffer();
            System.out.println("Sorry, I didn't understand that.");
            Game();
        }
    }

    private static Location[][] generateMaze(int mazeRows, int mazeCols){
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
        return dirs;//todo need to set start and end points
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

    private static void startMaze(){
        Location[][] maze = generateMaze(5, 5);
        RoomLists.add(maze);
        currLocation = maze;
        player.setCurrLocation(currLocation[0][0]);//todo update when I create the starting point and ending points
    }

    private static void startGame(){//todo maybe create a map so players can view where they have been with a setting to turn in on/off to make it easier/harder
        textBuffer();
        controller = new Controller();
        player = new Player();
        startMaze();//starts maze creation
        //do game stuff
        gameRunning = true;
        while (gameRunning) {
            textBuffer();
            System.out.println(player.toString());
            getCommand();
        }
    }

    private static void getCommand(){
        Command command = controller.Listen();
        if (!command.run()){
            unknownInput();
            System.out.println("\n" + player.toString());
            getCommand();
        }
    }

    public static void unknownInput(){
        Main.textBuffer();
        System.out.println("Sorry, I don't understand. Please try again.");
    }

    public static void textBuffer(){
        System.out.println("\n \n \n \n \n");
    }

    public static Player getPlayer() {
        return player;
    }
}
