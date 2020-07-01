import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static String name = "my text adventure";
    private static Scanner scanner;
    private static Player player;
    private static Controller controller;
    private static Location startingLoc;
    private static boolean gameRunning;
    //private HashMap<String, Location> map = new HashMap<>();

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

    private static void generateMaze(){
        //generate maze
        int key = 1;
        Location loc = new Location("Maze Room", key +"");
        loc.setDescription("in a dark stone hallway with a passage to the south");
        Location loc2 = new Location("Maze Room 2", (key+1) +"");
        loc2.setDescription("in a dark stone hallway with a passage to the north");
        loc.addConnection(loc2, "south");
        loc2.addConnection(loc, "north");
        startingLoc = loc;
    }

    private static void startGame(){
        textBuffer();
        controller = new Controller();
        player = new Player();
        generateMaze();
        player.setCurrLocation(startingLoc);
        //do game stuff
        gameRunning = true;
        while (gameRunning) {
            textBuffer();
            System.out.println(player.toString());
            Command command = controller.Listen();
            command.run();
        }
    }

    public static void textBuffer(){
        System.out.println("\n \n \n \n \n");
    }

}
