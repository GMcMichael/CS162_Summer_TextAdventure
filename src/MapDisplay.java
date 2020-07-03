import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;

public class MapDisplay extends JFrame {

    private int nodeDims;
    private boolean discover;
    private Location[][] currLocation;

    public MapDisplay(int nodeDims, Location[][] currLocation){//todo map display looks weird, its probably messed up
        super("Map");
        this.nodeDims = nodeDims;
        /*this.lineThickness = lineThickness;
        this.mapMargin = mapMargin;*/
        this.currLocation = currLocation;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize((currLocation.length+2)*nodeDims, (currLocation[0].length+2)*nodeDims);
        setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);

        makeMap(g);
    }

    private void makeMap(Graphics g){
        g.setColor(Color.black);
        if(!discover){
            for(int i = 0; i < currLocation.length; i++){
                for(int j = 0; j < currLocation[i].length; j++){
                    if(currLocation[i][j].getConnection("south") != null) drawSouth(i, j, g);
                    if(currLocation[i][j].getConnection("east") != null) drawEast(i, j, g);
                }
            }
        }
    }

    private void drawSouth(int x, int y, Graphics g){
        int x1 = (nodeDims * x) + nodeDims/4;
        int y1 = (nodeDims * y) + nodeDims/4;
        int x2 = (nodeDims * x) + (nodeDims - (nodeDims/4));
        int y2 = (nodeDims * (y+1)) + (nodeDims - (nodeDims/4));
        g.fillRect(x1, y2, Math.abs((x2-x1)), Math.abs((y2-y1)));
    }

    private void drawEast(int x, int y, Graphics g){
        int x1 = (nodeDims * x) + nodeDims/4;
        int y1 = (nodeDims * y) + nodeDims/4;
        int x2 = (nodeDims * (x+1)) + (nodeDims - (nodeDims/4));
        int y2 = (nodeDims * y) + (nodeDims - (nodeDims/4));
        g.fillRect(x1, y2, Math.abs((x2-x1)), Math.abs((y2-y1)));
    }

}
