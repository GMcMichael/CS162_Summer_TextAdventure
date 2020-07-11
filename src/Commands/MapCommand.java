public class MapCommand extends Command {

    private MapDisplay mapDisplay;

    public MapCommand() {
        super("map");
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
