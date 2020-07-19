public class OverworldMapCommand extends Command {

    private OverworldMapDisplay mapDisplay;

    public OverworldMapCommand() {
        super("omap");//short for Overworld Map
    }

    @Override
    boolean run() {
        mapDisplay = Main.getOverworldMapDisplay();
        mapDisplay.setVisible(!mapDisplay.isVisible());
        MapCommand.toggledWorld();
        return true;
    }

    public void setMapDisplay(OverworldMapDisplay mapDisplay){
        this.mapDisplay = mapDisplay;
    }
}
