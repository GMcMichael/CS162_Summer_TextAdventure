import java.util.ArrayList;

public class InventoryCommand extends Command {

    public InventoryCommand() {
        super("inventory");
    }

    @Override
    boolean run() {
        ArrayList<Item> inventory = Main.getPlayer().getInventory();
        if(inventory.size() == 0){
            System.out.println("You have 0 items in your inventory.");
            Main.waitForPress();
            return true;
        } else if(inventory.size() == 1)  System.out.println("You have 1 item in your inventory:");
        else System.out.println("You have " + inventory.size() + " items in your inventory:");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.print(inventory.get(i).getType());
            if(i < inventory.size()-1) System.out.print(", ");
        }
        System.out.println(".");
        Main.waitForPress();
        return true;
    }
}
