import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private WorldLocation currWorldLocation;
    private Location currLocation;
    private int health;
    private int food;
    private int water;
    private int sleep;
    private int stamina;
    private int money;
    private NPCharacter interacting;
    private HashMap<String, Item> equiped = new HashMap<>();
    private ArrayList<Item> inventory = new ArrayList<Item>();

    public Player(int health, int food, int water, int sleep, int stamina, int money){
        this.health = health;
        this.food = food;
        this.water = water;
        this.sleep = sleep;
        this.stamina = stamina;
        this.money = money;
    }

    public Player(){
        this(100, 100, 100, 100, 100, 15);
    }

    public WorldLocation getCurrWorldLocation() {
        return currWorldLocation;
    }

    public void setCurrWorldLocation(WorldLocation currWorldLocation) {
        this.currWorldLocation = currWorldLocation;
    }

    public Location getCurrLocation() {
        return currLocation;
    }

    public void setCurrLocation(Location currLocation) {
        this.currLocation = currLocation;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addItem(Item item){
        inventory.add(item);
    }

    public void removeItem(Item item){
        inventory.remove(item);
    }

    public NPCharacter getInteracting() {
        return interacting;
    }

    public void setInteracting(NPCharacter interacting) {
        this.interacting = interacting;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    @Override
    public String toString(){
        return "You are " + currLocation.toString() + ".";//get descriptions for health/stamina and stuff without saying direct numbers "you are healthy/ you are critically injured
    }
}
