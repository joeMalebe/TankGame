package UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import UI.StartScreen.StartGame;

import DesignPatterns.IContext;
import DesignPatterns.IState;

public class GameOverScreen extends Screen implements IState {

	
	GridBagLayout gb;
	GridBagConstraints c;
	JButton btnNewGame;
	public GameOverScreen(IContext wrapper)
	{
		super(wrapper);
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new Home());
		
		c = new GridBagConstraints();
		gb = new GridBagLayout();
		this.setLayout(gb);
		gb.setConstraints(this, c);
		this.add(btnNewGame,position(c,GridBagConstraints.CENTER,GridBagConstraints.CENTER));
		this.setSize(GameFrame.FRAME_WIDTH,GameFrame.FRAME_HEIGHT);
		this.setVisible(true);
		
		
		
		
		
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

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void goNext() {
		// TODO Auto-generated method stub
		this.getWrapper().setState(new StartScreen(this.getWrapper()));
	}

	@Override
	public void handle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IContext getWrapper() {
		// TODO Auto-generated method stub
		return null;
	}
	
	class Home implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			GameOverScreen.this.goNext();
		}
		
	}
	
}
