import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static String name = "my text adventure";
    private static Scanner scanner;
    private static Player player;
    private static Controller controller;
    private static boolean gameRunning;
    private static ArrayList<Location[][]> RoomLists = new ArrayList<Location[][]>();
    private static Location startLocation;
    private static Location[][] currLocation;
    private static MapDisplay mapDisplay;
    private static String mazeExitDir;

    public static void main(String[] args){
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

    private static void startMaze(){
        Location[][] maze = MazeGeneration.generateMaze(5, 5);
        RoomLists.add(maze);
        currLocation = maze;
        player.setCurrLocation(getStartLocation());
    }

    private static void startGame(){
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

    private static Location getStartLocation() {
        return startLocation;
    }

    public static void setStartLocation(Location startLocation, String dir) {
        startLocation.setMazeExit(true, dir, new Location("Maze Exit"));//set exit beside start location here
        Main.startLocation = startLocation;
    }

    public static String getMazeExitDir() {
        return mazeExitDir;
    }

    public static void setMazeExitDir(String mazeExitDir){
        Main.mazeExitDir = mazeExitDir;
    }

    public static MapDisplay getMapDisplay() {
        return mapDisplay;
    }

    public static void setMapDisplay(MapDisplay mapDisplay) {
        Main.mapDisplay = mapDisplay;
    }

    public static Location[][] getCurrLocation() {
        return currLocation;
    }
}
