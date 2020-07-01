public class Player {
    private Location currLocation;
    private int health;
    private int food;
    private int water;
    private int sleep;
    private int stamina;

    public Player(int health, int food, int water, int sleep, int stamina){
        this.health = health;
        this.food = food;
        this.water = water;
        this.sleep = sleep;
        this.stamina = stamina;
    }

    public Player(){
        this(100, 100, 100, 100, 100);
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

    @Override
    public String toString(){
        return "You are " + currLocation.toString() + ".";//get descriptions for health/stamina and stuff without saying direct numbers "you are healthy/ you are critically injured
    }
}
