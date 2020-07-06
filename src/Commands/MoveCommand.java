public class MoveCommand extends Command {

    public MoveCommand(String activator) {
        super(activator);
    }

    @Override
    boolean run() {
        Location newLoc = getCurrLocation().getConnection(getModifier());
        if(newLoc != null) setCurrLocation(newLoc);
        else return false;
        Controller.moved();
        return true;
    }

    private Location getCurrLocation(){
        return Main.getPlayer().getCurrLocation();
    }

    private void setCurrLocation(Location newLoc){
        Main.getPlayer().setCurrLocation(newLoc);
    }
}
