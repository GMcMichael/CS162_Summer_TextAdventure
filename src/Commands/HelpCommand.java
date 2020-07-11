import java.util.Scanner;

public class HelpCommand extends Command{

    String[] commandList = {"Help: This command displays all the commands, and their requirements if they have any.",
                            "Move: This command moves your player though the world, it requires a trailing cardinal direction to activate.",
                            "Map: This command open a map window to help you navigate the games locations easier.",
                            "Buy: This command attempts to buy an item identified by the trailing word.",
                            "Talk: This command attempts to start talking with another character by the name of the trailing word.",
                            "Interact: This command attempts to interact with an object using the trailing word to identify it."};

    public HelpCommand() {
        super("help");
    }

    @Override
    boolean run() {
        Main.textBuffer();
        System.out.println("Type a command word to run commands, some command words require trailing commands to be properly called. \n" +
                "Here are the current available commands and their requirements:\n");
        for (String s: commandList) {
            System.out.println("-" + s);
        }
        Main.waitForPress();
        return true;
    }
}
