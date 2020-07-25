import java.awt.Color;
import java.util.ArrayList;

public class TownGeneration {

    private static ArrayList<Location> townLocations;
    private static ArrayList<String> townLocNames;

    public static Location[][] generateTown(){
        return generateTown(true, true, true, true);
    }

    public static Location[][] generateTown(boolean castle, boolean tavern, boolean inn, boolean market){//todo add more places
        townLocations = new ArrayList<Location>();
        townLocNames = new ArrayList<String>();
        townLocations.add(generateSquare());
        if(castle) townLocations.add(generateCastle());
        if(tavern) townLocations.add(generateTavern());
        if(inn) townLocations.add(generateInn());
        if(market) townLocations.add(generateMarket());

        for (int i = 0; i < townLocations.size(); i++) {
            for (int j = 0; j < townLocations.size(); j++) {
                if(townLocations.get(j) != townLocations.get(i))townLocations.get(i).addConnection(townLocations.get(j), townLocNames.get(j));
            }
        }

        for (Location loc: townLocations) {//todo change NPC generation so more than one can be at a location
            if(Controller.randomNumber(0, 4) > 0) loc.addCharacter(new NPCharacter());
        }
        int num = (townLocations.size()+1)/2;
        Location [][] townLocs = new Location[num][num];

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if(((num*i)+j) < townLocations.size()){
                    townLocs[i][j] = townLocations.get((num*i)+j);
                    townLocations.get((num*i)+j).setMapColor(new Color(115, 100, Controller.randomNumber(10, 50)));
                    townLocations.get((num*i)+j).setType("town");
                }
            }
        }
        /*Main.setStartLocation(townLocs[0][0]);
        Main.setMapDisplay(new MapDisplay(Main.getPlayer(), 50, townLocs, 0, 0));*/
        return townLocs;
    }

    private static Location generateSquare(){
        townLocNames.add("square");
        Location squareLoc = new Location("Square");
        squareLoc.setDescription("in the towns main square");
        return squareLoc;
    }

    private static Location generateCastle(){
        townLocNames.add("castle");
        Location castleLoc = new Location("Castle");
        castleLoc.setDescription("in the towns castle");
        return castleLoc;
    }

    private static Location generateTavern(){
        townLocNames.add("tavern");
        Location tavernLoc = new Location("Tavern");
        tavernLoc.setDescription("in the towns tavern");
        return tavernLoc;
    }

    private static Location generateInn(){
        townLocNames.add("inn");
        Location tavernLoc = new Location("Inn");
        tavernLoc.setDescription("in the towns inn");
        return tavernLoc;
    }

    private static Location generateMarket(){
        townLocNames.add("market");
        Location tavernLoc = new Location("Market");
        tavernLoc.setDescription("in the towns market");
        return tavernLoc;
    }
}
