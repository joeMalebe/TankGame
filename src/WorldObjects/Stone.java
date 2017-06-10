package WorldObjects;

import java.awt.Point;

import DesignPatterns.IPaintable;
import DesignPatterns.IPainter;
import Graphics.GamePainter;

public class Stone extends WorldObject implements IPaintable {

	public Stone(Point point) {
		super(point);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(IPainter painter) {
		painter.paint(this);
		
	}

	@Override
	public void draw(GamePainter g) {
		// TODO Auto-generated method stub
		this.accept(g);
	}

}
