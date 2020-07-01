import java.util.HashMap;
import java.util.Scanner;

public class Controller {

    private Scanner scanner;
    private static HashMap<String, Command> allCommands = new HashMap<>();;
    private static Command command;

    public Controller(){
        scanner = new Scanner(System.in);
        new HelpCommand("help");
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
        Main.textBuffer();
        System.out.println("Sorry, I don't understand. Please try again.");
        Listen();
    }

    public static void addCommand(String key, Command command){
        allCommands.put(key, command);
    }
}
