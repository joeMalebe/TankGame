package WorldObjects;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import Graphics.GamePainter;

public abstract class WorldObject {
	private Rectangle rect;
	private Point point;
	private Dimension size;
	
	public WorldObject(Point point)
	{
		this. size = new Dimension(World.OBJ_WIDTH_SIZE, World.OBJ_HEIGHT_SIZE);
		
		this.point = point;
		this.rect = new Rectangle(point.x,point.y,(int)(size.getWidth()+10),(int)(size.getHeight()+10));
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public abstract void draw(GamePainter g);

	
	
	
	
}
