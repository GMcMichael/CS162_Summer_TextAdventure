import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;

public class OverworldMapDisplay extends JFrame {

    private int nodeDims;
    private boolean discover;
    private Player player;
    private WorldLocation[][] currLocation;
    private int currX;
    private int currY;
    private int xOffset;
    private int yOffset;

    public OverworldMapDisplay(Player player, int nodeDims, WorldLocation[][] currLocation, int sX, int sY){
        super("Overworld Map");
        this.player = player;
        this.nodeDims = nodeDims;
        this.currLocation = currLocation;
        this.currX = sX;
        this.currY = sY;
        this.xOffset = nodeDims/10;
        this.yOffset = (int) (nodeDims*0.6);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(((currLocation.length)*nodeDims) + xOffset*2, ((currLocation[0].length)*nodeDims) + yOffset*2);
        setLocationRelativeTo(null);
        setVisible(false);
        Controller.setOverworldMapDisplay(this);
    }

    public void paint(Graphics g){
        super.paint(g);
        makeMap(g);
    }

    private void makeMap(Graphics g){
        MapDisplay mapDisplay = Main.getMapDisplay();
        int localX = mapDisplay.getCurrX();
        int localY = mapDisplay.getCurrY();
        if(!discover){
            for(int i = 0; i < currLocation.length; i++){
                for(int j = 0; j < currLocation[i].length; j++){
                    if(currLocation[i][j] == null) break;
                    if(currLocation[i][j].getType().equalsIgnoreCase("town")){
                        g.setColor(currLocation[i][j].getMapColor());
                        g.fillRect((i*nodeDims) + xOffset, (j*nodeDims) + yOffset, nodeDims, nodeDims);
                        if(i == currX && j == currY){
                            g.setColor(Color.CYAN);
                            g.fillRect((i*nodeDims) + xOffset + (nodeDims/4), (j*nodeDims) + yOffset + (nodeDims/4), nodeDims/2, nodeDims/2);
                        }
                    } else {
                        Location[][] locations = currLocation[i][j].getAreaLocations();
                        for (int k = 0; k < locations.length; k++) {
                            for (int l = 0; l < locations[k].length; l++) {
                                int width = nodeDims / locations.length;
                                int height = nodeDims / locations[k].length;
                                g.setColor(locations[k][l].getMapColor());
                                int x = (i * nodeDims) + xOffset + (width * k);
                                int y = (j * nodeDims) + yOffset + (height * l);
                                g.fillRect(x, y, width, height);
                                if (i == currX && j == currY && k == localX && l == localY) {
                                    g.setColor(Color.CYAN);
                                    g.fillRect(x + (width / 4), y + (height / 4), width / 2, height / 2);
                                }
                            }
                        }
                    }
                    /*g.setColor(currLocation[i][j].getMapColor());
                    g.fillRect((i*nodeDims) + xOffset, (j*nodeDims) + yOffset, nodeDims, nodeDims);
                    if(i == currX && j == currY){//check for smaller squares as well
                        g.setColor(Color.CYAN);
                        g.fillRect((i*nodeDims) + xOffset + (nodeDims/4), (j*nodeDims) + yOffset + (nodeDims/4), nodeDims/2, nodeDims/2);
                    }*/
                }
            }
        }
    }

    public void getNewCoords(){
        for (int i = 0; i < currLocation.length; i++) {
            for (int j = 0; j < currLocation[i].length; j++) {
                if(currLocation[i][j] == player.getCurrWorldLocation()) {
                    currX = i;
                    currY = j;
                    i = currLocation.length;
                    break;
                }
            }
        }
        this.repaint();
    }

    public int getCurrX() {
        return currX;
    }

    public int getCurrY() {
        return currY;
    }
}
