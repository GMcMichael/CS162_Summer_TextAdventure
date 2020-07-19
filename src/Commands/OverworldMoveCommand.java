public class OverworldMoveCommand extends Command {

    private boolean leavingTown;
    private int moveDifficulty;

    public OverworldMoveCommand() {
        super("omove");//short for Overworld Move
    }

    @Override
    boolean run() {//todo use the moveDifficulty when adding time and player resource use (figure out how I am going to implement moveDifficulty, right now all hashmaps for it are empty)
        leavingTown = getCurrWorldLocation().isTown();
        WorldLocation newLoc = getCurrWorldLocation().getConnection(getModifier());
        if(newLoc != null) setCurrWorldLocation(newLoc);
        else return false;
        Controller.movedWorld();
        Main.getPlayer().setInteracting(null);
        setNewLocal();
        return true;
    }

    private void setNewLocal(){
        Location[][] locs = getCurrWorldLocation().getAreaLocations();
        Location newLoc = null;
        Player player = Main.getPlayer();
        try {
            if(leavingTown){
                if (getModifier().equalsIgnoreCase("north")) {
                    int x = locs.length/2;
                    newLoc = locs[x][locs[x].length - 1];
                } else if (getModifier().equalsIgnoreCase("south")) {
                    newLoc = locs[locs.length/2][0];
                } else if (getModifier().equalsIgnoreCase("east")) {
                    newLoc = locs[0][locs[0].length/2];
                } else if (getModifier().equalsIgnoreCase("west")) {
                    int y = locs[locs.length-1].length/2;
                    newLoc = locs[locs.length - 1][y];
                }
            } else {
                if (getModifier().equalsIgnoreCase("north")) {
                    int x = Main.getMapDisplay().getCurrX();
                    newLoc = locs[x][locs[x].length - 1];
                } else if (getModifier().equalsIgnoreCase("south")) {
                    newLoc = locs[Main.getMapDisplay().getCurrX()][0];
                } else if (getModifier().equalsIgnoreCase("east")) {
                    newLoc = locs[0][Main.getMapDisplay().getCurrY()];
                } else if (getModifier().equalsIgnoreCase("west")) {
                    int y = Main.getMapDisplay().getCurrY();
                    newLoc = locs[locs.length - 1][y];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
          newLoc = locs[0][0];
        }
        if(getCurrWorldLocation().isTown()) newLoc = locs[0][0];
        player.setCurrLocation(newLoc);
        Main.getMapDisplay().setCurrLocation(locs);
    }

    private WorldLocation getCurrWorldLocation(){
        return Main.getPlayer().getCurrWorldLocation();
    }

    private void setCurrWorldLocation(WorldLocation newLoc){
        Main.getPlayer().setCurrWorldLocation(newLoc);
    }

}
