package UI;

import java.awt.Container;

import javax.swing.JFrame;

import DesignPatterns.*;
import Graphics.Frame;

public class GameFrame extends JFrame implements IContext {
	public static final int FRAME_WIDTH = 1080;
	public static final int FRAME_HEIGHT = 700;
	Screen current;
	Container contain;
	public GameFrame()
	{
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);	
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.current = new StartScreen(this);
		this.add(current);
	}

	public void goNext() {
		// TODO Auto-generated method stub
		current.goNext();
	}

	public void setState(Screen screen) {
		contain = this.getContentPane();
		contain.removeAll();
		contain.add(screen);
		screen.setFocusable(true);
		screen.requestFocus();
		invalidate();
		repaint();
		
		//this.repaint();
	}
}
