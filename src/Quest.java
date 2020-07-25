public class Quest {

    private QuestItem questItem;
    private static int baseReward = 10;
    private int reward;

    private void simpleQuest(){
        questItem = new QuestItem(this);

        WorldLocation[][] worldLocations = Main.getWorld();
        int x = Controller.randomNumber(0, worldLocations.length);
        int y = Controller.randomNumber(0, worldLocations[x].length);
        WorldLocation worldLocation = worldLocations[x][y];
        Location[][] locations = worldLocation.getAreaLocations();
        int x2 = Controller.randomNumber(0, locations.length);
        int y2 = Controller.randomNumber(0, locations[x2].length);
        Location questLocation = locations[x2][y2];
        questLocation.addItem(questItem);
        double dist = Math.sqrt( Math.pow((x - Main.getOverworldMapDisplay().getCurrX()), 2) + Math.pow((y - Main.getOverworldMapDisplay().getCurrY()), 2));
        reward = (int) (baseReward * dist);
    }

    public void completeQuest(){
        Player player = Main.getPlayer();
        player.setMoney(player.getMoney() + reward);//todo remove the questItem and Quest from wherever they are stored when completed
    }
}
