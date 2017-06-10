package Tanks;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import List.IList;
import List.IPosition;
import List.List;
import Stack.Stack;
import WorldObjects.Brick;
import WorldObjects.Road;
import WorldObjects.World;
import WorldObjects.WorldObject;
import DesignPatterns.IPaintable;
import DesignPatterns.IPainter;

public class PlayerTank extends Tank{

	private String playerName;
	public final int   SPEED = 6;
	public final int WIDTH = 40;
	public final int HEIGHT = 40;
	protected List<Tank> enemies;
	public int kills; 
	//private World world;

	public PlayerTank(World world)
	{	
		super(world);
		this.setPosition(new Point(20,20)) ;
		this.setSize(new Dimension(this.WIDTH,this.HEIGHT));
		this.speed = SPEED;
		this.rect = new Rectangle(this.position,this.getSize());
		this.setDirection(Direction.Right); 
		this.enemies = new List<Tank>();
		this.setLife(100);
		kills = 0;
	}




	public PlayerTank(World world,int life, Dimension size, Direction direction, int speed,
			Point position, String playerName) {
		super(world,life, size, direction, speed, position);
		this.playerName = playerName;
		this.setDirection(Direction.Right);
	}

	
	public void addTarget(Enemy e)
	{
		this.getEnemeies().addLast(e);
		Stack<Bullet> b = new Stack<Bullet>();
		for(int i = 0;i < 5;i++)
		{
			b.push(new Bullet(this.world,this.getEnemeies()));
		}
		this.setBullets(b);
	}

	public void setEnemies(List<Tank> e)
	{
		this.enemies = e;
		this.setBullets(e);
	}

	public List<Tank> getEnemeies()
	{
		return this.enemies;
	}

	private void setBullets(List<Tank> t)
	{
		for(int i = 0;i < 5;i++)
		{
			bullets.push(new Bullet(this.world,t));
		}
	} 

	@Override
	protected void reload()
	{
		for(int i = 0;i < 5;i++)
		{
			bullets.push(new Bullet(this.world,this.enemies));
		}
	}


	@Override
	public void accept(IPainter painter) {
		// TODO Auto-generated method stub
		painter.paint(this);

	}

	@Override
	protected boolean enemyCollision()
	{
		if(this.enemies.isEmpty())
		{
			return false;
		}
		else
		{	
			for(IPosition<Tank> t:this.enemies)
			{
				if(this.rect.intersects(t.getElement().getRect()))
				{
					System.out.println("collision");
					return true;
				}
				//else
				//	return false;
			}
		}
		return false;
	}




	@Override
	protected boolean objectCollision() {
		
		if(world.getWorldObjects().isEmpty())
		{
			return false;
		}
		else
		{
			for(WorldObject obj:world.getWorldObjects())
			{
				if(!(obj instanceof Road))
				{
					if((obj instanceof Brick)){
						if(((Brick)obj).isDestroyed())
						{
							continue;
						}
					}
					
					if(this.rect.intersects(obj.getRect()))
					{
						System.out.println("collision with");
						return true;
					}
				}
			}
		}
		
		
		return false;
	}






}
