import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;

public class MapDisplay extends JFrame {

    private int nodeDims;
    private boolean discover;
    private Player player;
    private Location[][] currLocation;
    private int currX;
    private int currY;
    private int xOffset;
    private int yOffset;

    public MapDisplay(Player player, int nodeDims, Location[][] currLocation, int sX, int sY){
        super("Map");
        this.player = player;
        this.nodeDims = nodeDims;
        /*this.lineThickness = lineThickness;
        this.mapMargin = mapMargin;*/
        this.currLocation = currLocation;
        this.currX = sX;
        this.currY = sY;
        this.xOffset = nodeDims/10;
        this.yOffset = (int) (nodeDims*0.6);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize((currLocation.length+1)*nodeDims, (currLocation[0].length+1)*nodeDims);
        setLocationRelativeTo(null);
        setVisible(false);
        Controller.setMapDisplay(this);
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
                    if(currLocation[i][j] == null) break;
                    if(currLocation[i][j].getConnection("south") != null) drawSouth(i, j, g);
                    if(currLocation[i][j].getConnection("east") != null) drawEast(i, j, g);
                }
            }
            drawNodes(g);
        }
    }

    private void drawSouth(int x, int y, Graphics g){
        int x1 = (nodeDims * x) + nodeDims/4;
        int y1 = (nodeDims * y) + nodeDims/4;
        int x2 = (nodeDims * x) + (nodeDims - (nodeDims/4));
        int y2 = (nodeDims * (y+1)) + (nodeDims - (nodeDims/4));
        int xOffset = nodeDims/10;
        int yOffset = (int) (nodeDims*0.6);
        g.fillRect(x1 + xOffset, y1 + yOffset, Math.abs((x2-x1)), Math.abs((y2-y1)));
    }

    private void drawEast(int x, int y, Graphics g){
        int x1 = (nodeDims * x) + nodeDims/4;
        int y1 = (nodeDims * y) + nodeDims/4;
        int x2 = (nodeDims * (x+1)) + (nodeDims - (nodeDims/4));
        int y2 = (nodeDims * y) + (nodeDims - (nodeDims/4));
        int xOffset = 5;
        int yOffset = (int) (nodeDims*0.6);
        g.fillRect(x1 + xOffset, y1 + yOffset, Math.abs((x2-x1)), Math.abs((y2-y1)));
    }

    private void drawNodes(Graphics g){
        int offset = nodeDims/4;
        int sideLength = nodeDims - (nodeDims/4);
        for(int i = 0; i < currLocation.length; i++){
            for(int j = 0; j < currLocation[i].length; j++){
                if(currLocation[i][j] == null) break;
                if(i == currX && j == currY) g.setColor(Color.CYAN);
                else g.setColor(currLocation[i][j].getMapColor());
                g.fillRect((i*nodeDims) + offset, (nodeDims/2) + (j*nodeDims) + offset, sideLength, sideLength);
            }
        }
    }

    public void getNewCoords(){
        for (int i = 0; i < currLocation.length; i++) {
            for (int j = 0; j < currLocation[i].length; j++) {
                if(currLocation[i][j] == player.getCurrLocation()) {
                    currX = i;
                    currY = j;
                    i = currLocation.length;
                    break;
                }
            }
        }
        this.repaint();
    }

    public void setCurrLocation(Location[][] currLocation) {
        this.currLocation = currLocation;
        getNewCoords();
        setSize((currLocation.length+1)*nodeDims, (currLocation[0].length+1)*nodeDims);
    }

    public void close(){
        this.dispose();
    }

    public int getCurrX() {
        return currX;
    }

    public int getCurrY() {
        return currY;
    }
}
