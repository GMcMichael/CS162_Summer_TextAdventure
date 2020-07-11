import java.util.HashMap;

public class NPCharacter {

    private String name;
    private static String[] names = {"Jeff", "Adam", "James", "Bree", "Jess", "Gwen"};
    private String desc;//todo set and use description
    private int money = Controller.randomNumber(15, 201);
    private String personality;
    private static String[] personalities = {"Bold", "Shy", "Mean", "Kind"};
    private int tradeModifier = 1;
    private HashMap<String, Item> selling;
    private Quest quest;
    private Player player;
    private static int maxWares = 5;

    public NPCharacter(){
        this.player = Main.getPlayer();
        personality = personalities[Controller.randomNumber(0, personalities.length)];
        name = names[Controller.randomNumber(0, names.length)];
        generateWares();
    }

    public void talk(){
        Main.textBuffer();
        player.setInteracting(this);
        System.out.println(getIntro());
        System.out.println(getWares());
    }

    private String getIntro(){
        String intro = getName() + " > ";
        switch (personality){
            case "Bold":
                intro += "Hello! My name is " + getName() + "! Please, look at my wares!";
                break;
            case "Shy":
                intro += "Hello, would you like to buy anything?";
                break;
            case "Mean":
                intro += "Either buy something or get lost.";
                break;
            case "Kind":
                intro += "Hello! My name is " + getName() + "! Can I help you with anything today?";
                break;
        }
        return intro;
    }

    private String getThanks(){
        String thanks = getName() + " > ";
        switch (personality){
            case "Bold":
                thanks += "That's a great choice! Come back again!";
                break;
            case "Shy":
                thanks += "Thank you, have a nice day.";
                break;
            case "Mean":
                thanks += "Great, now scram.";
                break;
            case "Kind":
                thanks += "Thank you for the purchase! Come back anytime!";
                break;
        }
        return thanks;
    }

    private void generateWares(){
        selling = new HashMap<>();
        if(Controller.randomNumber(0, 5) == 0) return;
        int num = Controller.randomNumber(0, maxWares);
        for (int i = 0; i < num; i++) {
            int type = Controller.randomNumber(0, Item.getNumOfTypes());
            switch (type) {//todo make more items and add them here
                case 0:
                    Item item = new SwordItem(Controller.randomNumber(0, 4));
                    selling.put(item.getType().toLowerCase(), item);
                    break;
            }
        }
    }

    public String getWares(){
        if(selling == null || selling.size() == 0) return name + " doesn't have anything to sell";
        String wares = name + " has";
        int remaining = selling.size();
        for (Item item: selling.values()) {
            wares += " a " + item.getType();
            remaining--;
            if(remaining != 0) wares += ",";
            else wares += ".";
        }
        return wares;
    }

    public void sell(String type) {
        Item item = selling.get(type);
        if(item == null) {
            System.out.println("I'm sorry I don't understand <" + type + ">");
            Main.waitForPress();
            return;
        }
        int price = getPrice(item);
        if(player.getMoney() >= price) {
            player.setMoney(player.getMoney() - price);
            selling.remove(type, item);
            player.addItem(item, type);
            System.out.println(getThanks());
            Main.waitForPress();
        } else {
            System.out.println("You don't have enough money, it costs " + price + " gold. You need " + (price - player.getMoney()) + " more gold");
            Main.waitForPress();
        }
    }

    private int getPrice(Item item){
        return item.getWorth() * tradeModifier;
    }

    public void generateQuest(){
        quest = new Quest();
    }

    public void removeQuest(){
        quest = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTradeModifier() {
        return tradeModifier;
    }

    public void setTradeModifier(int tradeModifier) {
        this.tradeModifier = tradeModifier;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
