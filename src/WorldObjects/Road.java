package WorldObjects;

import java.awt.Point;

import DesignPatterns.IPaintable;
import DesignPatterns.IPainter;
import Graphics.GamePainter;

public class Road extends WorldObject implements IPaintable {

	public Road(Point point) {
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
		this.accept(g);
		
	}

}
