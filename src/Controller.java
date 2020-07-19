import java.util.HashMap;
import java.util.Scanner;

public class Controller {

    private Scanner scanner;
    private static HashMap<String, Command> allCommands = new HashMap<>();;
    private static Command command;
    private static MapDisplay mapDisplay;
    private static OverworldMapDisplay OverworldMapDisplay;
    private Player player;

    public Controller(){
        player = Main.getPlayer();
        scanner = new Scanner(System.in);
        new HelpCommand();
        new MoveCommand();
        new MapCommand();
        new OverworldMapCommand();
        new BuyCommand();
        new TalkCommand();
        new InteractCommand();
        new OverworldMoveCommand();
        new InventoryCommand();
    }

    public Command Listen(){
        command = null;
        System.out.print("\nWhat would you like to do? \n--- \n> ");
        String nextLine = scanner.nextLine();
        String word1 = null, word2 = null;
        Scanner parser = new Scanner(nextLine);
        if(parser.hasNext()) word1 = parser.next();
        else unknownInput();
        if (parser.hasNext()) word2 = parser.next();

        command = allCommands.get(word1.toLowerCase());
        if(command == null) unknownInput(word1);
        else if(word2 != null) command.setModifier(word2.toLowerCase());
        else command.setModifier(null);
        return command;
    }

    private void unknownInput(){
        Main.unknownInput();
    }

    private void unknownInput(String error){
        Main.textBuffer();
        System.out.println("Sorry, I don't understand <" + error + ">");
        Main.waitForPress();
        Main.textBuffer();
    }

    public static void addCommand(String key, Command command){
        allCommands.put(key, command);
    }

    /**
     * Generates a random number from the inclusive minimum to the exclusive maximum
     * @param min The inclusive minimum integer
     * @param max The exclusive maximum integer
     * @return A variable of type int
     */
    public static int randomNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void setMapDisplay(MapDisplay mapDisplay){
        Controller.mapDisplay = mapDisplay;
    }

    public static void setOverworldMapDisplay(OverworldMapDisplay overworldMapDisplay) {
        OverworldMapDisplay = overworldMapDisplay;
    }

    public static void moved(){
        if(mapDisplay != null) mapDisplay.getNewCoords();
    }

    public static void movedWorld(){
        if(OverworldMapDisplay != null) OverworldMapDisplay.getNewCoords();
    }
}
