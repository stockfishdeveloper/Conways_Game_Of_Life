import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Draw_Board implements ActionListener, MouseListener, MouseMotionListener {

    JFrame frame;
    JPanel panel;
    JButton Start;
    JButton Stop;
    JButton Fast;
    JButton Medium;
    JButton Slow;
    JButton Clear;
    JButton Save;
    JButton Recall;
    static int delay; // used to determine the delay between generations
    static Board_Rep component; // our life component
    static boolean animate = false; // are we animating right now?
    static int[] SaveBoardx = new int[10000]; // arrays to hold the board state
    static int[] SaveBoardy = new int[10000];
    static int SaveCount; // used to determine when we're done saving
    static boolean isDown = false; // is the user holding down the cursor?
    static boolean[][] GameBoard = new boolean[100][100]; // the actual life gameboard

    public Draw_Board() {
        Start = new JButton("Start"); // add all our buttons to the component
        Stop = new JButton("Stop");
        Fast = new JButton("Fast");
        Medium = new JButton("Medium");
        Slow = new JButton("Slow");
        Clear = new JButton("Clear");
        Save = new JButton("Save");
        Recall = new JButton("Recall");
        panel = new JPanel();
        
        // make sure each cell is dead to begin with
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                GameBoard[i][j] = false;
            }
        }
        // and make sure the save board array is empty too
        for (int j = 0; j < 10000; j++) {
            SaveBoardx[j] = 0;
            SaveBoardy[j] = 0;
        }
        
        // set up our variables
        SaveCount = 0;
        delay = 300;
        frame = new JFrame("Life");
        frame.setSize(1200, 1000);
        panel.add(Start);
        panel.add(Stop);
        panel.add(Clear);
        panel.add(Fast);
        panel.add(Medium);
        panel.add(Slow);
        panel.add(Save);
        panel.add(Recall);
        Start.setActionCommand("Start");
        Start.addActionListener(this);
        Stop.setActionCommand("Stop");
        Stop.addActionListener(this);
        Clear.setActionCommand("Clear");
        Clear.addActionListener(this);
        Fast.setActionCommand("Fast");
        Fast.addActionListener(this);
        Medium.setActionCommand("Medium");
        Medium.addActionListener(this);
        Slow.setActionCommand("Slow");
        Slow.addActionListener(this);
        Save.setActionCommand("Save");
        Save.addActionListener(this);
        Recall.setActionCommand("Recall");
        Recall.addActionListener(this);
        panel.setPreferredSize(new Dimension(80, 100));
        panel.setMaximumSize(panel.getPreferredSize());
        panel.setMinimumSize(panel.getPreferredSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        component = new Board_Rep();
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        component.setPreferredSize(new Dimension(970, 970));
        component.setMaximumSize(panel.getPreferredSize());
        component.setMinimumSize(panel.getPreferredSize());
        frame.add(component, BorderLayout.WEST);
        frame.add(panel, BorderLayout.EAST);
        frame.setVisible(true);
    }
    
    // our action response methods
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Stop")) {
            animate = false;
        }
        if (e.getActionCommand().equals("Start")) {
            animate = true;
        }
        if (e.getActionCommand().equals("Fast")) {
            delay = 0;
        }
        if (e.getActionCommand().equals("Medium")) {
            delay = 150;
        }
        if (e.getActionCommand().equals("Slow")) {
            delay = 300;
        }
        if (e.getActionCommand().equals("Clear")) {
            // clear the gameboard
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    GameBoard[i][j] = false;
                }
            }
            // and stop animating and repaint the board
            animate = false;
            component.paintImmediately(0, 0, 970, 970);
        }
        if (e.getActionCommand().equals("Save")) {
            // set the save board to zero to be sure
            SaveCount = 0;
            for (int j = 0; j < 10000; j++) {
                SaveBoardx[j] = 0;
                SaveBoardy[j] = 0;
            }
            
            // go cell by cell comparing if the current cell is alive, and record it accordingly
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (GameBoard[i][j] == true) {
                        SaveBoardx[SaveCount] = i;
                        SaveBoardy[SaveCount++] = j;
                    }
                }
            }
        }
        // clear the actual gamebord in anticipation of recall
        if (e.getActionCommand().equals("Recall")) {
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    GameBoard[i][j] = false;
                }
            }
            // for each alive cell in the recall array, set the game board cell to alive
            for (int i = 0; i < Draw_Board.SaveCount; i++) {
                GameBoard[SaveBoardx[i]][SaveBoardy[i]] = true;
            }
            // and repaint
            component.paintImmediately(0, 0, 970, 970);
        }
    }
    
    // our user input response methods
    @Override
    public void mousePressed(MouseEvent m) {
        // get the current mouse location
        int x = m.getX();
        int y = m.getY();
        // fund the current cell the user is pointing to and toggle it
        Draw_Board.GameBoard[x / 9][y / 9] = Draw_Board.GameBoard[x / 9][y / 9] != true;
        
        // repaint and set the down variable
        component.paintImmediately(0, 0, 970, 970);
        Draw_Board.isDown = true;

    }

    @Override
    public void mouseEntered(MouseEvent m) {
    }

    @Override
    public void mouseExited(MouseEvent m) {
    }

    @Override
    public void mouseReleased(MouseEvent m) {
        // the user is done selecting cells
        Draw_Board.isDown = false;
    }

    @Override
    public void mouseClicked(MouseEvent m) {
    }

    public void mouseDown(MouseEvent m) {
    }

    public void mouseUp(MouseEvent m) {

    }

    @Override
    public void mouseMoved(MouseEvent m) {
    }

    @Override
    public void mouseDragged(MouseEvent m) {
        // if the user is dragging the mouse
        // get current pointer location
        int x = m.getX();
        int y = m.getY();
        
        // set the current cell to alive
        Draw_Board.GameBoard[x / 9][y / 9] = true;
        
        // and repaint
        component.paintImmediately(0, 0, 970, 970);
    }

    public void mouseMove(MouseEvent arg0) {
    }
}
