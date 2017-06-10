package UI;

import javax.swing.JPanel;

import DesignPatterns.*;

public abstract class Screen extends JPanel implements IState{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IContext wrapper;
	public Screen(IContext wrapper)
	{
		this.wrapper = wrapper;
	}
	
	public IContext getWrapper()
	{
		return this.wrapper;
	}
}
