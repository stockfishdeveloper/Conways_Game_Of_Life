import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.awt.geom.*;
class Draw_Board implements ActionListener, MouseListener
{
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
	static int delay;
	static Board_Rep component;
	static boolean animate = false;
	static int[] SaveBoardx = new int[10000];
	static int[] SaveBoardy = new int[10000];
	static int SaveCount;
	static boolean[][] GameBoard = new boolean[100][100];
	
public Draw_Board()
{
	Start = new JButton("Start");
	Stop = new JButton("Stop");
	Fast = new JButton("Fast");
	Medium = new JButton("Medium");
	Slow = new JButton("Slow");
	Clear = new JButton("Clear");
	Save = new JButton("Save");
	Recall = new JButton("Recall");
	panel = new JPanel();
	for(int i = 0; i < 100; i++)
	{
		for(int j = 0; j < 100; j++)
		{
			GameBoard[i][j] = false;
		}
	}
	for(int j = 0; j < 10000; j++)
	{
		SaveBoardx[j] = 0;
		SaveBoardy[j] = 0;
	}
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
	component = new Board_Rep();
	component.addMouseListener(this);
	component.setPreferredSize(new Dimension(970, 970));
	component.setMaximumSize(panel.getPreferredSize());
	component.setMinimumSize(panel.getPreferredSize());
	frame.add(component, BorderLayout.WEST);
	frame.add(panel, BorderLayout.EAST);
	frame.setVisible(true);
}
void Run()
{
	while(animate == true)
	{
	Update_GameBoard.Next_Generation();
	component.paintImmediately(0, 0, 970, 970);
	//component.paintComponent(component.getGraphics());
	//panel.update(panel.getGraphics());
	/*try {
		Thread.sleep(300);
	} catch (InterruptedException e1) {
		e1.printStackTrace();
	}*/
	}
}

public void actionPerformed(ActionEvent e) 
{
	if(e.getActionCommand().equals("Stop"))
	{
		animate = false;
	}
	if(e.getActionCommand().equals("Start"))
	{
		animate = true;
		
	}
	if(e.getActionCommand().equals("Fast"))
	{
		delay = 0;
	}
	if(e.getActionCommand().equals("Medium"))
	{
		delay = 150;
	}
	if(e.getActionCommand().equals("Slow"))
	{
		delay = 300;
	}
	if(e.getActionCommand().equals("Clear"))
	{
		for(int i = 0; i < 100; i++)
		{
			for(int j = 0; j < 100; j++)
			{
				GameBoard[i][j] = false;
			}
		}
		animate = false;
		component.paintImmediately(0, 0, 970, 970);
	}
	if(e.getActionCommand().equals("Save"))
	{
		SaveCount = 0;
		for(int j = 0; j < 10000; j++)
		{
			SaveBoardx[j] = 0;
			SaveBoardy[j] = 0;
		}
		for(int i = 0; i < 100; i++)
		{
			for(int j = 0; j < 100; j++)
			{
				if(GameBoard[i][j] == true)
				{
					SaveBoardx[SaveCount] = i;
					SaveBoardy[SaveCount++] = j;
				}
			}
		}
	}
	if(e.getActionCommand().equals("Recall"))
	{
		for(int i = 0; i < 100; i++)
		{
			for(int j = 0; j < 100; j++)
			{
				GameBoard[i][j] = false;
			}
		}
		for(int i = 0; i < Draw_Board.SaveCount; i++)
		{
			GameBoard[SaveBoardx[i]][SaveBoardy[i]] = true;
		}
		component.paintImmediately(0, 0, 970, 970);
	}
}
public void mousePressed(MouseEvent m) 
{
	int x = m.getX();
	int y = m.getY();
	if(Draw_Board.GameBoard[x / 9][y / 9] == true)
	{
		Draw_Board.GameBoard[x / 9][y / 9] = false;
	}
	else
	{
		Draw_Board.GameBoard[x / 9][y / 9] = true;
	}
	component.paintImmediately(0, 0, 970, 970);
	
}
@Override
public void mouseEntered(MouseEvent m) {
}
@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseClicked(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


}

@SuppressWarnings("serial")
class Board_Rep extends JComponent
{

int[] Rack1 =  new int[100];
int[] Rack2 =  new int[100];

Board_Rep()
{
	for(int i = 0; i < 100; i++)
	{
		Rack1[i] = i * 9;
	}
	for(int i = 0; i < 100; i++)
	{
		Rack2[i] = i * 9;
	}
}
public void paintComponent(Graphics comp)
{
Graphics2D comp2D = (Graphics2D)comp;
comp2D.setColor(Color.red);
for(int j = 0; j < 100; j++)
{
for(int i = 0; i < 100; i++)
{
	if((Draw_Board.GameBoard[j][i]) == true)
	{	comp2D.setColor(Color.green);
		Rectangle2D.Float recky = new Rectangle.Float((Rack1[j]), (Rack2[i]), 8F, 8F);
		comp2D.fill(recky);
		comp2D.setColor(Color.red);
	}
	else
	{
	comp2D.setColor(Color.red);
	Rectangle2D.Float recky = new Rectangle.Float((Rack1[j]), (Rack2[i]), 8F, 8F);
	comp2D.fill(recky);
	}

}
}
}
}


public class Life
{
public static void main(String[] args)
{
		
Draw_Board draw = new Draw_Board();
while(true)
{
	if(Draw_Board.animate == true)
	{
Update_GameBoard.Next_Generation();
Draw_Board.component.paintImmediately(0, 0, 970, 970);
//component.paintComponent(component.getGraphics());
//panel.update(panel.getGraphics());
try {
	Thread.sleep(Draw_Board.delay);
} catch (InterruptedException e1) {
	e1.printStackTrace();
}
}
}
}
}

class Update_GameBoard
{
	static void Next_Generation()
	{
		int numtodie = 0;
		int numtolive = 0;
		int[] cellstodiex = new int[10000];
		int[] cellstodiey = new int[10000];
		int[] cellstolivex = new int[10000];
		int[] cellstolivey = new int[10000];
		for(int j = 0; j < 100; j++)
		{
			for(int i = 0; i < 100; i++)
			{
				int f = Popcount(j, i);
				if(Draw_Board.GameBoard[j][i] == false)
				{
					if(f == 3)
					{
						cellstolivex[numtolive] = j; 
						cellstolivey[numtolive++] = i;
						//Draw_Board.GameBoard[j][i] = true;
					}
				}
				if(Draw_Board.GameBoard[j][i] == true)
				{
					if((f > 3) || (f < 2))
					{
						cellstodiex[numtodie] = j; 
						cellstodiey[numtodie++] = i;
						//Draw_Board.GameBoard[j][i] = false;
					}
				}
			}
		}
		for(int i = 0; i < numtodie; i++)
		{
			Draw_Board.GameBoard[cellstodiex[i]][cellstodiey[i]] = false;
		}
		for(int i = 0; i < numtolive; i++)
		{
			Draw_Board.GameBoard[cellstolivex[i]][cellstolivey[i]] = true;
		}
	}
	
	static int Popcount(int x, int y)
	{
		int count = 0;
		if( x == 0)
		{
			if(y == 0)
			{
				if(Draw_Board.GameBoard[0][1] == true)
					count++;
				if(Draw_Board.GameBoard[1][1] == true)
					count++;
				if(Draw_Board.GameBoard[1][0] == true)
					count++;
				return count;
			}
			if(y == 99)
			{
				if(Draw_Board.GameBoard[0][98] == true)
					count++;
				if(Draw_Board.GameBoard[1][99] == true)
					count++;
				if(Draw_Board.GameBoard[1][98] == true)
					count++;
				return count;
			}
				if(Draw_Board.GameBoard[0][y + 1] == true)
					count++;
				if(Draw_Board.GameBoard[0][y - 1] == true)
					count++;
				if(Draw_Board.GameBoard[1][y] == true)
					count++;
				if(Draw_Board.GameBoard[1][y + 1] == true)
					count++;
				if(Draw_Board.GameBoard[1][y - 1] == true)
					count++;
				return count;
		}
		if(x == 99)
		{
			if(y == 0)
			{
				if(Draw_Board.GameBoard[99][1] == true)
					count++;
				if(Draw_Board.GameBoard[98][1] == true)
					count++;
				if(Draw_Board.GameBoard[98][0] == true)
					count++;
				return count;
			}
			if(y == 99)
			{
				if(Draw_Board.GameBoard[99][98] == true)
					count++;
				if(Draw_Board.GameBoard[98][99] == true)
					count++;
				if(Draw_Board.GameBoard[98][98] == true)
					count++;
				return count;
			}
				if(Draw_Board.GameBoard[99][y + 1] == true)
					count++;
				if(Draw_Board.GameBoard[99][y - 1] == true)
					count++;
				if(Draw_Board.GameBoard[98][y] == true)
					count++;
				if(Draw_Board.GameBoard[98][y + 1] == true)
					count++;
				if(Draw_Board.GameBoard[98][y - 1] == true)
					count++;
				return count;
		}
		if((y == 0) && (x != 0) && (x != 99))
		{
			if(Draw_Board.GameBoard[x - 1][0] == true)
				count++;
			if(Draw_Board.GameBoard[x + 1][0] == true)
				count++;
			if(Draw_Board.GameBoard[x][1] == true)
				count++;
			if(Draw_Board.GameBoard[x - 1][1] == true)
				count++;
			if(Draw_Board.GameBoard[x + 1][1] == true)
				count++;
			return count;
		}
		if((y == 99) && (x != 0) && (x != 99))
		{
			if(Draw_Board.GameBoard[x - 1][99] == true)
				count++;
			if(Draw_Board.GameBoard[x + 1][99] == true)
				count++;
			if(Draw_Board.GameBoard[x][98] == true)
				count++;
			if(Draw_Board.GameBoard[x - 1][98] == true)
				count++;
			if(Draw_Board.GameBoard[x + 1][98] == true)
				count++;
			return count;
		}
		if(Draw_Board.GameBoard[x + 1][y] == true)
			count++;
		if(Draw_Board.GameBoard[x - 1][y] == true)
			count++;
		if(Draw_Board.GameBoard[x][y + 1] == true)
			count++;
		if(Draw_Board.GameBoard[x][y - 1] == true)
			count++;
		if(Draw_Board.GameBoard[x + 1][y + 1] == true)
			count++;
		if(Draw_Board.GameBoard[x + 1][y - 1] == true)
			count++;
		if(Draw_Board.GameBoard[x - 1][y + 1] == true)
			count++;
		if(Draw_Board.GameBoard[x - 1][y - 1] == true)
			count++;
		return count;
	}
}
