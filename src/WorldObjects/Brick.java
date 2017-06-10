package WorldObjects;

import java.awt.Point;

import DesignPatterns.IPaintable;
import DesignPatterns.IPainter;
import Graphics.GamePainter;

public class Brick extends WorldObject implements IPaintable{

	private boolean destroy = false;
	public Brick(Point point) {
		super(point);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(IPainter painter) {
		// TODO Auto-generated method stub
		painter.paint(this);
	}

	@Override
	public void draw(GamePainter g) {
		// TODO Auto-generated method stub
		this.accept(g);
	}
	
	public void setDestroy(boolean destroy)
	{
		this.destroy = destroy;
	}
	
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return destroy;
	}
	

}
