package Graphics;

import java.awt.Color;

import javax.swing.JFrame;

import DesignPatterns.IContext;

import UI.GamePlayScreen;
import UI.Screen;

public class Frame extends JFrame implements IContext{

	public static final int FRAME_WIDTH = 500;
	public static final int FRAME_HEIGHT = 500;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Frame()
	{
		
		//this.add(new GamePlayScreen(this));
		
	}

	public void goNext() {
		// TODO Auto-generated method stub
		
	}

	public void setState(Screen screen) {
		// TODO Auto-generated method stub
		
	}
}
