package UI;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import DesignPatterns.*;
import WorldObjects.Road;
import WorldObjects.World;

public class StartScreen extends Screen implements IState {

	GridBagLayout gb;
	GridBagConstraints c;
	JButton btnNewGame;
	JButton btnLevel;
	Image img = null;
	World world;
	public StartScreen(IContext wrapper)
	{
		super(wrapper);
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new StartGame());
		btnLevel = new JButton("Level");
		btnLevel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				char[][] level = new char[20][20];
				String filename = null;
				JFileChooser jfc = new JFileChooser("./");
				int returnVal = jfc.showOpenDialog(StartScreen.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {

					filename = jfc.getSelectedFile().getPath();
					level = ReadGameLevel.gameLevel(filename);
					if(level != null)
					{

						world = new World(level);
					}
				}
			}
		});

		c = new GridBagConstraints();
		gb = new GridBagLayout();
		this.setLayout(gb);
		gb.setConstraints(this, c);
		this.setSize(GameFrame.FRAME_WIDTH,GameFrame.FRAME_HEIGHT);
		this.setVisible(true);




		this.add(btnNewGame,position(c,GridBagConstraints.CENTER,GridBagConstraints.CENTER));
		//this.add(btnLevel,position(c,GridBagConstraints.CENTER,GridBagConstraints.CENTER));
		
		//Image from http://www.animatedimages.org/cat-tanks-590.htm
		this.img = new ImageIcon("./data/animated-tankgif.gif").getImage(); 	
	}

	/**
	 * Set the coordinates for the grid bag constraints and return them
	 * @param c the grid bag constraints
	 * @param x the x position
	 * @param y the y position
	 * @return 
	 */
	private GridBagConstraints position(GridBagConstraints c,int x,int y)
	{
		c.gridx = x;
		c.gridy = y;
		return c;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, GameFrame.FRAME_WIDTH,GameFrame.FRAME_HEIGHT, this);

	}

	public void goNext() {

		// TODO Auto-generated method stub
		world = new World(ReadGameLevel.gameLevel("./data/levels/level0.txt"));
		for(int x = 0; x < 20;x++)
		{
			//System.out.println();
			for(int y = 0; y < 20; y++)
			{

				System.out.print(world.getLevel()[x][y]);
			}
			//System.out.println();
		}
		System.out.println();
		this.getWrapper().setState(new GamePlayScreen(this.getWrapper(),world));
	}

	public void handle() {
		// TODO Auto-generated method stub
		
	}

	class StartGame implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {

			StartScreen.this.goNext();
		}

	}



}
