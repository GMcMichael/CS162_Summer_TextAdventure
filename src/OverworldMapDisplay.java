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
        setSize((currLocation.length+1)*nodeDims, (currLocation[0].length+1)*nodeDims);
        setLocationRelativeTo(null);
        setVisible(false);
        Controller.setOverworldMapDisplay(this);
    }

    public void paint(Graphics g){
        super.paint(g);
        makeMap(g);
    }

    private void makeMap(Graphics g){
        if(!discover){
            for(int i = 0; i < currLocation.length; i++){
                for(int j = 0; j < currLocation[i].length; j++){
                    if(currLocation[i][j] == null) break;
                    g.setColor(currLocation[i][j].getMapColor());
                    g.fillRect((i*nodeDims) + xOffset, (j*nodeDims) + yOffset, nodeDims, nodeDims);
                    if(i == currX && j == currY){
                        g.setColor(Color.CYAN);
                        g.fillRect((i*nodeDims) + xOffset + (nodeDims/4), (j*nodeDims) + yOffset + (nodeDims/4), nodeDims/2, nodeDims/2);//todo make this more accurate based on players position within the local world location
                    }
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

}
