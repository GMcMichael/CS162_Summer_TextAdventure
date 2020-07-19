public class MapCommand extends Command {

    private static boolean world;
    private MapDisplay mapDisplay;
    private OverworldMapDisplay mapDisplay2;

    public MapCommand() {
        super("map");
    }

    @Override
    boolean run() {
        if(getModifier() != null) {
            if (getModifier().toLowerCase().charAt(0) == 'w') {
                toggleWorld();
                return true;
            } else if (getModifier().toLowerCase().charAt(0) == 'l') {
                toggleLocal();
                return true;
            }
        }
        if(world) toggleWorld();
        else toggleLocal();
        return true;
    }

    private void toggleWorld(){
        world = true;
        mapDisplay2 = Main.getOverworldMapDisplay();
        mapDisplay2.setVisible(!mapDisplay2.isVisible());
    }

    private void toggleLocal(){
        world = false;
        mapDisplay = Main.getMapDisplay();
        mapDisplay.setVisible(!mapDisplay.isVisible());
    }

    public static void toggledWorld(){
        System.out.println(world);
        world = false;
        System.out.println(world);
    }

    public void setMapDisplay(MapDisplay mapDisplay){
        this.mapDisplay = mapDisplay;
    }
}
