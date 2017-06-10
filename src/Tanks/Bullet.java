package Tanks;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import DesignPatterns.IPaintable;
import DesignPatterns.IPainter;

import List.IList;
import List.IPosition;
import List.List;
import List.ListNode;
import UI.GameFrame;
import UI.GameOverScreen;
import WorldObjects.Brick;
import WorldObjects.Stone;
import WorldObjects.World;
import WorldObjects.WorldObject;

public class Bullet implements IPaintable{
	Direction direction;
	int speed;
	Rectangle rect;
	Point position;
	private Dimension size;
	private List<Tank> targets;
	protected Tank enemy;
	private PlayerTank p;
	boolean hit;
	boolean fired;
	private World world;
	private final int SPEED = 10;
	protected final int POWER = 2;

	public Bullet(World world)
	{
		//this.target = new List<Tank>();
		this.world = world;
		size = new Dimension(15,25);
		this.fired = false;
		this.rect = new Rectangle(size);
		this.targets = new List<Tank>();
	}

	public Bullet(World world,Tank t)
	{
		this.world = world;
		//this.target = new List<Tank>();
		size = new Dimension(15,25);
		this.fired = false;
		this.rect = new Rectangle(size);
		this.targets = new List<Tank>();
		this.enemy = t;


		//targets.addFirst(t);
	}



	public Bullet(World world,List<Tank> targets)
	{
		this.world = world;
		size = new Dimension(15,25);
		this.fired = false;
		this.rect = new Rectangle(size);

		this.fired = false;
		this.targets = targets;

	}

	public Bullet(World world,PlayerTank player)
	{
		this.world = world;
		p = player;
		this.targets = p.getEnemeies();
	}

	public void addTarget(Tank t)
	{
		this.targets.addFirst(t);
	}
	/**
	 * @return the size
	 */
	public Dimension getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(Dimension size) {
		this.size = size;
	}


	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}
	/**
	 * @return the rect
	 */
	public Rectangle getRect() {
		return rect;
	}
	/**
	 * @param rect the rect to set
	 */
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	/**
	 * @return the p
	 */
	public Point getPosition() {
		return position;
	}
	/**
	 * @param p the p to set
	 */
	public void setPosition(Point p) {
		this.position = p;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * @return the fired
	 */
	public boolean isFired() {
		return fired;
	}
	/**
	 * @param fired the fired to set
	 */
	public void setFired(boolean fired) {
		this.fired = fired;
	}
	/**
	 * @return the hit
	 */
	public boolean isHit() {
		return hit;
	}



	public boolean isHit(Rectangle r)
	{


		if(this.rect.intersects(r))
		{
			this.hit = true;
			//this.fired = false;
			return hit;
		}






		return false;
	}
	/**
	 * @param hit the hit to set
	 */
	public void setHit(boolean hit) {
		this.hit = hit;
	}


	public void shoot(Direction direction,Point p)
	{
		this.setDirection(direction);
		this.position = p;
		this.fired = true;

	}

	public void setEnemy(Tank enemy)
	{
		this.enemy = enemy;
	}
	public boolean objectHit()
	{
		for(WorldObject o: this.world.getWorldObjects())
		{
			if((o instanceof Stone) || (o instanceof Brick))
			{
				if((o instanceof Brick))
				{
					if(!((Brick)o).isDestroyed())
					{
						if(this.rect.intersects(o.getRect()))
						{
							this.hit = true;
							
								((Brick) o).setDestroy(true);
							return hit;
						}
					}
				}
				else
				{
					if(this.rect.intersects(o.getRect()))
					{
						this.hit = true;

						return hit;
					}
				}
				

			}
		}
		return false;
	}

	public void travel()
	{
		if(this.enemy == null)
		{
			if(!this.targets.isEmpty())
			{
				for(IPosition<Tank> t :targets)
				{
					if(this.isHit(t.getElement().getRect()))
					{
						t.getElement().decreaseLife(POWER);
						System.out.println("player bullet hit");
						if(!t.getElement().alive)
						{
							targets.remove(t);
							//p.kills++;
							//IPosition<Tank> removePosition = new ListNode<Tank>(null,null,t) ;
							//targets.remove(removePosition);
						}
						//hit = false;
						fired = false;
						return;
					}
					else if(this.objectHit())
					{
						fired = false;
						return;
					}
				}
			}
		}
		else
		{
			if(this.isHit(enemy.getRect()))
			{
				enemy.decreaseLife(POWER);
				fired = false;
				System.out.println("bullet hit");
				return;
			}
			else if(this.objectHit())
			{
				fired = false;
				return;
			}

		}




		switch(this.direction)
		{
		case Up:
		{
			this.moveUp();
			break;
		}
		case Down:
		{
			this.moveDown();
			break;
		}

		case Left:
		{
			this.moveLeft();
			break;
		}

		case Right:
		{
			this.moveRight();
			break;
		}
		}
	}







	/**
	 * Moves the Bullet Down
	 */
	public void moveDown()
	{
		int x = (int) this.position.getX();
		int y = (int) this.position.getY();

		//		for(Tank t:target)
		//		{
		//			if(this.isHit(t.getRect()))
		//			{
		//				t.setLife(t.getLife() - POWER);
		//
		//			}
		//		}
		if(y >= (GameFrame.FRAME_HEIGHT - this.size.getHeight() - 31))
		{
			hit = true;
			return;
		}
		//if(this.rect.intersects(this.ta))
		/*for(Tank t :targets)
			{
				if(this.isHit(t.getRect()))
				{
					return;
				}
			}*/

		y += SPEED;



		this.rect.setLocation(new Point(x,y));
		this.setPosition(new Point(x,y));


	}

	/**
	 * Moves the Bullet Up
	 */
	public void moveUp()
	{
		int x = (int) this.position.getX();
		int y = (int) this.position.getY();



		if(y <= 0)
		{
			hit = true;
			return;
		}

		/*	for(Tank t :targets)
			{
				if(this.isHit(t.getRect()))
				{
					return;
				}
			}*/
		y -= SPEED;


		this.rect.setLocation(new Point(x,y));
		this.setPosition(new Point(x,y));


	}

	/**
	 * Moves the Bullet Left
	 */
	public void moveLeft()
	{
		int x = (int) this.position.getX();
		int y = (int) this.position.getY();

		//		for(Tank t:target)
		//		{
		//			if(this.isHit(t.getRect()))
		//			{
		//				t.setLife(t.getLife() - POWER);
		//
		//			}
		//		}
		if(x <= 0)
		{
			hit = true;
			return;
		}

		/*for(Tank t :targets)
			{
				if(this.isHit(t.getRect()))
				{
					return;
				}
			}*/

		x -= SPEED;


		this.rect.setLocation(new Point(x,y));
		this.setPosition(new Point(x,y));


	}

	/**
	 * Moves the Bullet Right
	 */
	public void moveRight()
	{
		int x = (int) this.position.getX();
		int y = (int) this.position.getY();

		if(x >= (GameFrame.FRAME_WIDTH - this.size.getWidth() - 9))
		{
			hit = true;
			return;
		}
		//		for(Tank t:target)
		//		{
		//			if(this.isHit(t.getRect()))
		//			{
		//				t.setLife(t.getLife() - POWER);
		//
		//			}
		//		}
		/*for(Tank t :targets)
			{
				if(this.isHit(t.getRect()))
				{
					return;
				}
			}
		 */
		x += SPEED;


		this.rect.setLocation(new Point(x,y));
		this.setPosition(new Point(x,y));


	}
	public void fire(Point p)
	{
		this.position = p;
		fired = true;
	}
	@Override
	public void accept(IPainter painter) {
		painter.paint(this);
	}

	public void animate(Graphics g,long count, BufferedImage bomb,int x,int y)
	{

		if(count < 5000 )
		{
			g.drawImage(bomb, x,y, 40,40, null);
			count += 5;
		}
		if(count >= 5000)
		{
			count = 0;
		}


	}
}
