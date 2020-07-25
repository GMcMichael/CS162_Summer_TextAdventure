public class MoveCommand extends Command {

    public MoveCommand() {
        super("move");
    }

    @Override
    boolean run() {
        Location newLoc = getCurrLocation().getConnection(getModifier());
        if(newLoc != null) setCurrLocation(newLoc);
        else return false;
        Controller.moved();
        Controller.movedWorld();
        Main.getPlayer().setInteracting(null);
        return true;
    }

    private Location getCurrLocation(){
        return Main.getPlayer().getCurrLocation();
    }

    private void setCurrLocation(Location newLoc){
        Main.getPlayer().setCurrLocation(newLoc);
    }
}
