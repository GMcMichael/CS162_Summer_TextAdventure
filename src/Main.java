import java.util.Scanner;

public class Main {

    private static String name = "my text adventure";
    private static Scanner scanner;
    private static Player player;
    private static Controller controller;
    private static boolean gameRunning;
    private static WorldLocation[][] world;
    private static Location startLocation;
    private static WorldLocation startWorldLocation;
    private static Location[][] currLocation;
    private static MapDisplay mapDisplay;
    private static OverworldMapDisplay overworldMapDisplay;
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
        currLocation = maze;
        player.setCurrLocation(getStartLocation());
    }

    private static void startTown(){
        Location[][] town = TownGeneration.generateTown(true, true, true, true);
        currLocation = town;
        player.setCurrLocation(getStartLocation());
    }

    private static void startPath(){
        /*Location[][] path = PathGeneration.generatePath();//broke because I made it need a WorldLocation passed in to set the worlds color
        currLocation = path;
        player.setCurrLocation(getStartLocation());*/
    }

    private static void startWorld(){
        world = WorldMapGeneration.generateWorld(5, 5);
        /*for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(world[i][j].getType() + ", ");
            }
            System.out.println("");
        }
        waitForPress();*/
        player.setCurrLocation(getStartLocation());
        player.setCurrWorldLocation(getStartWorldLocation());
    }

    private static void startGame(){//todo add food and water needs
        textBuffer();
        player = new Player();
        controller = new Controller();
        //startMaze();//maze works, just set exit to an actual location in actual use
        //startTown();//town works
        //startPath();//path works but this function is broken for now
        startWorld();
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
        if (command == null || !command.run()){
            System.out.println(player.toString());
            getCommand();
        }
    }

    public static void unknownInput(){
        Main.textBuffer();
        System.out.println("Sorry, I don't understand. Please try again.");
        waitForPress();
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

    public static void setStartLocation(Location startLocation) {
        Main.startLocation = startLocation;
    }

    public static WorldLocation getStartWorldLocation() {
        return startWorldLocation;
    }

    public static void setStartWorldLocation(WorldLocation startWorldLocation) {
        Main.startWorldLocation = startWorldLocation;
    }

    public static MapDisplay getMapDisplay() {
        return mapDisplay;
    }

    public static void setMapDisplay(MapDisplay mapDisplay) {
        Main.mapDisplay = mapDisplay;
    }

    public static OverworldMapDisplay getOverworldMapDisplay() {
        return overworldMapDisplay;
    }

    public static void setOverworldMapDisplay(OverworldMapDisplay overworldMapDisplay) {
        Main.overworldMapDisplay = overworldMapDisplay;
    }

    public static Location[][] getCurrLocation() {
        return currLocation;
    }

    public static void waitForPress(){
        System.out.print("\nPress enter to continue. \n--- \n> ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

}
