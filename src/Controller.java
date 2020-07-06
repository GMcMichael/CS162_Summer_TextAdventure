import java.util.HashMap;
import java.util.Scanner;

public class Controller {

    private Scanner scanner;
    private static HashMap<String, Command> allCommands = new HashMap<>();;
    private static Command command;
    private static MapDisplay mapDisplay;

    public Controller(){
        scanner = new Scanner(System.in);
        new HelpCommand("help");
        new MoveCommand("move");
        new MapCommand("map");
    }

    public Command Listen(){
        command = null;
        System.out.print("\nWhat would you like to do? \n--- \n> ");
        String nextLine = scanner.nextLine();
        String word1 = null, word2 = null;
        Scanner parser = new Scanner(nextLine);
        if(parser.hasNext()) word1 = parser.next();
        else unknownInput();
        if(parser.hasNext()) word2 = parser.next();

        command = allCommands.get(word1);
        if(command == null) unknownInput();
        command.setModifier(word2);
        return command;
    }

    private void unknownInput(){
        Main.unknownInput();
        Listen();
    }

    public static void addCommand(String key, Command command){
        allCommands.put(key, command);
    }

    public static int randomNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void setMapDisplay(MapDisplay mapDisplay){
        Controller.mapDisplay = mapDisplay;
    }

    public static void moved(){
        mapDisplay.getNewCoords();
    }
}
