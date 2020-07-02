import java.util.Scanner;

public class HelpCommand extends Command{

    String[] commandList = {"Help: This command displays all the commands and their requirements if they have any.",
                            "Move: This command moves your player though the world, it requires a trailing cardinal direction to activate"};

    public HelpCommand(String activator) {
        super(activator);
    }

    @Override
    boolean run() {
        Main.textBuffer();
        System.out.println("Type a command word to run commands, some command words require trailing commands to be properly called. \n" +
                "Here are the current available commands and their requirements:\n");
        for (String s: commandList) {
            System.out.println("-" + s);
        }
        System.out.print("\nPress enter to continue. \n--- \n> ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        return true;
    }
}
