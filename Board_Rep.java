import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

// our board rep class, where we have the screen cordinates of each cell
@SuppressWarnings("serial")
class Board_Rep extends JComponent {
    
    // arrays to hold coordinates
    int[] Rack1 = new int[100];
    int[] Rack2 = new int[100];

    Board_Rep() {
        // each cell is 10 pixels wide, include one for the line drawn between cells 
        for (int i = 0; i < 100; i++) {
            Rack1[i] = i * 9;
        }
        for (int i = 0; i < 100; i++) {
            Rack2[i] = i * 9;
        }
    }

    // our paint method
    @Override
    public void paintComponent(Graphics comp) {
        Graphics2D comp2D = (Graphics2D) comp;
        comp2D.setColor(Color.LIGHT_GRAY);
        // for each cell on the board
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 100; i++) {
                // if the cell is alive, then paint it black
                if ((Draw_Board.GameBoard[j][i]) == true) {
                    comp2D.setColor(Color.black);
                    Rectangle2D.Float recky = new Rectangle.Float((Rack1[j]), (Rack2[i]), 8F, 8F);
                    comp2D.fill(recky);
                    comp2D.setColor(Color.LIGHT_GRAY);
                }
                // otherwise paint it light gray
                else {
                    comp2D.setColor(Color.LIGHT_GRAY);
                    Rectangle2D.Float recky = new Rectangle.Float((Rack1[j]), (Rack2[i]), 8F, 8F);
                    comp2D.fill(recky);
                }
            }
        }
    }
}
