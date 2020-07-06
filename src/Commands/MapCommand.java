public class MapCommand extends Command {

    private MapDisplay mapDisplay;

    public MapCommand(String activator) {
        super(activator);
    }

    @Override
    boolean run() {
        mapDisplay = Main.getMapDisplay();
        mapDisplay.setVisible(!mapDisplay.isVisible());
        return true;
    }

    public void setMapDisplay(MapDisplay mapDisplay){
        this.mapDisplay = mapDisplay;
    }
}
