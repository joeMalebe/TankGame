/**
 * 
 */
package Tanks;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import DesignPatterns.IPaintable;

import List.IList;
import List.List;
import Stack.Stack;
import UI.GameFrame;
import UI.GamePlayScreen;
import WorldObjects.World;




/**
 * @author Malebe Joe 201301041
 *
 */
public abstract class Tank implements IPaintable {

	private int life;
	private Dimension size;
	protected Direction direction;
	protected int speed;
	protected Point position;
	protected int centreX;
	protected int centreY;
	protected boolean shoot;
	protected Tank enemy;
	protected Tank friend;
	protected boolean readyToLaunch;
	protected boolean alive;
	protected World world;

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	public void refreshWorld(World world)
	{
		this.world = world;
	}

	protected Rectangle rect;
	protected Stack<Bullet> bullets;
	Bullet bullet;
	private boolean rightCollision;
	private boolean leftCollision;
	private boolean downCollision;
	private boolean topCollision;
	//temporary constructor
	public Tank(World world,int life, Dimension size, Direction direction, int speed,
			Point position) {
		super();
		this.life = life;
		this.size = size;
		this.direction = direction;
		this.world = world;
		this.speed = speed;
		this.position = position;
		this.rect = new Rectangle(this.position,this.size);
		bullets = new Stack<Bullet>();
		alive = true;
	
	}
	
	/**
	 * Creates a default Tank object
	 */
	public Tank(World world) {
		this.world = world;
		bullet = new Bullet(world);
		bullets = new Stack<Bullet>();
		for(int i = 0;i < 5;i++)
		{
			bullets.push(new Bullet(world));
		}
		readyToLaunch = true;
		alive = true;
		
	}

	
	
	/**
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life the life to set
	 */
	public void setLife(int life) {
		this.life = life;
	}




	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @return the centreX
	 */
	public int getCentreX() {
		centreX = this.position.x + (this.size.width/2);
		return centreX;
	}

	/**
	 * @return the centreY
	 */
	public int getCentreY() {
		centreY = this.position.y + (this.size.height/2);
		return centreY;
	}

	/**
	 * @return the enemy
	 */
	public Tank getEnemy() {
		return enemy;
	}


	/**
	 * @param enemy the enemy to set
	 */
	public void setEnemy(Tank enemy) {
		this.enemy = enemy;
		//this.enemies.addFirst(enemy);
		
		for(int i = 0;i < 5;i++)
		{
			this.bullets.push(new Bullet(this.world,enemy));
		}
	}
	
	public void decreaseLife(int power)
	{
		this.life -= power;
		if(this.life <= 0)
		{
			this.alive = false;
		}
	}
	
	
	

	/**
	 * @return the friend
	 */
	public Tank getFriend() {
		return friend;
	}


	/**
	 * @param friend the friend to set
	 */
	public void setFriend(Tank friend) {
		this.friend = friend;
	}


	/**
	 * @return the size
	 */
	public Dimension getSize() {
		return size;
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
	 * @return the position
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * @return the shoot
	 */
	public boolean isShoot() {
		return shoot;
	}


	/**
	 * @param shoot the shoot to set
	 */
	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}


	/**
	 * @return the bullets
	 */
	public Stack<Bullet> getBullets() {
		return bullets;
	}


	/**
	 * @param bullets the bullets to set
	 */
	public void setBullets(Stack<Bullet> bullets) {
		this.bullets = bullets;
	}


	

	/**
	 * Checks if the Tank collided with an enemy
	 * @return True if the enemy has been collided and false if not
	 */
	 protected abstract boolean enemyCollision();
	 
	 protected abstract boolean objectCollision();
	/*{
		
		if(this.rect.intersects(this.getEnemy().getRect()))
		{
			//System.out.println("collision");
			return true;
		}
		else
			return false;
	}*/
	
	protected void reload()
	{
		for(int i = 0; i < 5;i++)
		{
			this.bullets.push(new Bullet(this.world,this.enemy));
		}
	}
	
	
	
	/**
	 * Checks whether the tank has a collision while moving in the vertical direction
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return whether the move is valid or not
	 */
	protected boolean legalMove(int x,int y)
	{

		//this.setPosition(new Point(x,y));
		this.rect.setLocation(new Point(x,y));
		if(this.enemyCollision() ||this.objectCollision())
		{
			return false;
		}
		
		return true;
	}
	/**
	 * Moves the Tank Down
	 */
	public void moveDown()
	{
		int x = (int) this.position.getX();
		int y = (int) this.position.getY();
		int tempy = y;
		this.direction = Direction.Down;

		if(y >= (GamePlayScreen.GAME_PLAY_HEIGHT - this.size.getHeight() - 31))
		{
			return;
		}

		/*if(this.enemyCollision())
		{
			//this.setPosition(new Point(x,y));

			if(this.downCollision)
			{
				//return;
			}
		}*/

		tempy += speed;
		if(this.legalMove(x, tempy))
		{
			y = tempy;
			this.rect.setLocation(new Point(x,y));
			this.setPosition(new Point(x,y));
			return;
		}
		else
		{

			//y += speed;

			
			this.rect.setLocation(new Point(x,y));
			//this.setPosition(new Point(x,y));
			return;
		}
	}

	/**
	 * Moves the Tank up
	 */
	public void moveUp()
	{

		int x = (int) this.position.getX();
		int y = (int) this.position.getY();
		int tempy = y;
		this.direction = Direction.Up;

		if(y <= 0)
		{
			return;
		}
		/*if(this.enemyCollision())
		{
			//this.setPosition(new Point(x,y));
			if(this.topCollision)
			{
				//return;
			}
		}*/


//		y -= speed;
		tempy -= speed;
		if(this.legalMove(x, tempy))
		{
			y= tempy;
			this.rect.setLocation(new Point(x,y));
			this.setPosition(new Point(x,y));
			return;
		}
		else
		{

			//y -= speed;
			
			this.rect.setLocation(new Point(x,y));
			//this.setPosition(new Point(x,y));
			return;
		}
		
	}

	/**
	 * Moves the Tank Left
	 */
	public void moveLeft()
	{
		int x = (int) this.position.getX();
		int y = (int) this.position.getY();
		int tempx = x;
		this.direction = Direction.Left;

		if(x <= 0 )
		{
			return;
		}

		/*if(this.enemyCollision())
		{
			//this.setPosition(new Point(x,y));

			if(this.leftCollision)
			{
				//return;
			}
		}*/
		
		tempx -= speed;
		if(this.legalMove(tempx,y))
		{
			x = tempx;
			this.rect.setLocation(new Point(x,y));
			this.setPosition(new Point(x,y));
			return;
		}
		else
		{

		//	x -= speed;
			
			this.rect.setLocation(new Point(x,y));
			//this.setPosition(new Point(x,y));
			return;
		}
		
//		x -= speed;
		
	}

	/**
	 * Moves the Tank Right
	 */
	public void moveRight()
	{


		int x = (int) this.position.getX();
		int y = (int) this.position.getY();
		int tempx = x;
		
		this.direction = Direction.Right;
		if(x >= (GamePlayScreen.GAME_PLAY_WIDTH - this.size.getWidth() - 9))
		{
			return;
		}

/*		if(this.enemyCollision())
		{
			//this.setPosition(new Point(x,y));
			if(this.rightCollision)
			{
				return;
			}
		}
*/
		//x += speed;
		tempx += speed;
		if(this.legalMove(tempx,y))
		{
			x = tempx;
			this.rect.setLocation(new Point(x,y));
			this.setPosition(new Point(x,y));
			return;
		}
		else
		{

			//x += speed;
			
			this.rect.setLocation(new Point(x,y));
			return;
			//this.setPosition(new Point(x,y));
			
		}
		
	}
	public Bullet getBullet()
	{
		return bullet;
	}
	
	
	public void shoot()
	{
		//Bullet b = bullets.pop();
		//b.fire(this.getPosition());
		if(bullets.isEmpty())
		{
			reload();
			shoot =false;
			readyToLaunch = false;
			return;
		}
		if(readyToLaunch)
		{
			this.shoot = true;
			this.bullet = bullets.pop();
			readyToLaunch = false;
		}
		if(this.bullet.isHit())
		{
			//shoot = false;
			readyToLaunch = true;
			bullet.setFired(false);
			bullet.setHit(false);
			//bullet = new Bullet();
		}
		//this.bullet.shoot(this.getDirection(), this.getPosition());
		//this.bullet.shoot(this.direction, this.position);		
	}




}
