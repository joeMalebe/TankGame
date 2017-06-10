package Tanks;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import BinaryTrees.BTNode;
import BinaryTrees.BinaryTree;
import BinaryTrees.IBTPosition;
import List.IPosition;
import List.List;
import WorldObjects.Brick;
import WorldObjects.Road;
import WorldObjects.World;
import WorldObjects.WorldObject;
import DesignPatterns.IPaintable;
import DesignPatterns.IPainter;

public class Enemy extends Tank implements  Runnable, ActionListener {

	public final int SPEED = 2;
	public final int WIDTH = 40;
	public final int HEIGHT = 40;
	public final int MOVE_UP = 1;
	public final int MOVE_DOWN = 2;
	public final int MOVE_RIGHT = 3;
	public final int MOVE_LEFT = 4;
	public final int SHOOT = 5;
	//private World world;

	private  int YES_PROBABILITY = 55;
	private final int LIFE = 6;
	private boolean alive;
	private boolean pause;
	private int chance = 0;
	private int keyCode;
	private Timer t;
	Random r;
	private List<Enemy> friends;
	private boolean decision;
	private BinaryTree<String> decisionTree;
	public Enemy(World world,int life, Dimension size, Direction direction, int speed,
			Point position) {
		super(world,life, size, direction, speed, position);
		pause = false;
	}
	
	public int randInt(  Random rand,int min, int max) {

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public Enemy(World world,Point p)
	{
		super(world);
		this.setPosition(p);
		this.setSize(new Dimension(this.WIDTH, this.HEIGHT));
		this.speed = SPEED;
		/*t = new Timer(5, this);
		t.start();
		Y
		
		*/this.rect = new Rectangle(this.position,this.getSize());
		pause = false;
		this.setDirection(Direction.Left); 
		r = new Random(this.hashCode());
		this.setLife(LIFE);
		this.friends = new List<Enemy>();
		this.YES_PROBABILITY = this.randInt(r, 65, 100);
		
		this.initTree();
	}
	
	public Enemy(World world) {
		super(world);
		this.setPosition(new Point(200, 300));
		this.setSize(new Dimension(this.WIDTH, this.HEIGHT));
		this.speed = SPEED;
		/*t = new Timer(5, this);
		t.start();
		*/this.rect = new Rectangle(this.position,this.getSize());
		pause = false;
		this.setDirection(Direction.Left); 
		r = new Random(this.hashCode());
		this.setLife(LIFE);
		this.friends = new List<Enemy>();
		this.YES_PROBABILITY = this.randInt(r, 65, 100);
		
		chance = (int) (Math.random() * 100);

		this.initTree();
	}
	
	public void init()
	{
		t = new Timer(5, this);
		t.start();
		new Thread(this).start();
		
	}

	private void initTree()
	{
		IBTPosition<String> root = new BTNode<String>("Do you want to follow Player ?",null,null,null);
		decisionTree = new BinaryTree<String>(root);

		decisionTree.addLeft(decisionTree.root(), "You will follow, Do you want to move Horizontally ?");
		decisionTree.addRight(decisionTree.root(), "You won't follow, Do you want to move Horizontally ?");
		decisionTree.addLeft(decisionTree.root().getLeftChild(), "follow horizontally");
		decisionTree.addRight(decisionTree.root().getLeftChild(), "follow vertically");
		decisionTree.addLeft(decisionTree.root().getRightChild(), "not follow horizontally");
		decisionTree.addRight(decisionTree.root().getRightChild(), "not follow vertically");
		
	}

	public List<Enemy> getFriends()
	{
		return this.friends;
	}
	private int difference(int d1,int d2)
	{
		if(d1 > d2)
			return d1 - d2;
		else if(d2 > d1)
			return d2 - d1;
		else return d1;
	}
	
	@Override
	protected boolean objectCollision()
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
					if(this.rect.intersects(obj.getRect()))
					{
						this.shoot();
						return true;
					}
					
				}
				if(this.rect.intersects(obj.getRect()))
				{
					
					return true;
				}
			}
			
		}
		return false;
	}
	
	
	@Override
	protected boolean enemyCollision()
	{
		if(this.rect.intersects(this.getEnemy().getRect()))
		{
			//System.out.println("collision");
			return true;
		}

		if(this.friends.isEmpty())
		{
			return false;
		}
		else
		{	
			for(IPosition<Enemy> t:this.friends)
			{
				if(this.rect.intersects(t.getElement().getRect()))
				{
					System.out.println("friendly collision");
					return true;
				}
				//else
					//return false;
			}
		}
		return false;

	}

	private boolean xLineOfSight()
	{
		int xMin = enemy.position.x - 25;
		int xMax = enemy.position.x + enemy.getSize().width + 25;
		if(this.getCentreX() >= xMin && this.getCentreX() <= xMax)
		{
			if(this.enemy.position.y > this.position.y && this.direction == Direction.Down)
			{
				return true;
			}
			else if(this.enemy.position.y < this.position.y && this.direction == Direction.Up)
			{
				return true;
			}
			else return false;
		}
		else 
			return false;
		
	}

	private boolean yLineOfSight()
	{
		/*int yMin = this.enemy.position.y;
		int yMax = this.enemy.position.y + this.enemy.getSize().height;
		 */
		int yMin = enemy.position.y - 25;
		int yMax = enemy.position.y + enemy.getSize().height + 25;

		if(this.getCentreY() >= yMin && this.getCentreY() <= yMax)
		{
			if(this.enemy.position.x > this.position.x && this.direction == Direction.Right)
			{
				return true;
			}
			else if(this.enemy.position.x < this.position.x && this.direction == Direction.Left)
			{
				return true;
			}
			else return false;
		}
		else 
			return false;
	}



	/**
	 * The tank will follow its enemy Vertically
	 */
	private void followPlayerVertical() {

		/*if(this.xLineOfSight())
		{
			this.keyCode = this.SHOOT;
			return;
		}*/
		if (this.enemy.getPosition().y > this.position.y) {

			this.keyCode = this.MOVE_DOWN;

		}
		if (this.enemy.getPosition().y <= this.position.y) {
			this.keyCode = this.MOVE_UP;
		}


	}




	/**
	 * The tank will follow its enemy Horizontally
	 */
	private void followPlayerHorizontal() {
		/*if(this.yLineOfSight())
		{
			this.keyCode = this.SHOOT;
			return;
		}*/
		if(difference(this.enemy.getPosition().y,this.position.y) > difference(this.enemy.getPosition().x,this.position.x))
		{
			this.followPlayerVertical();
			return;
		}
		if (this.enemy.getPosition().x >= this.position.x) {

			this.keyCode = this.MOVE_RIGHT;
		}
		if (this.enemy.getPosition().x < this.position.x) {
			this.keyCode = this.MOVE_LEFT;
		}




	}


	/**
	 * The tank will travel opposite direction to his enemy horizontally 
	 */
	private void notfollowHorizontal() {
		if (this.enemy.getPosition().x > this.position.x) {

			this.keyCode = this.MOVE_LEFT;
		}
		if (this.enemy.getPosition().x <= this.position.x) {
			this.keyCode = this.MOVE_RIGHT;
		}

	}

	/**
	 * The tank will travel opposite direction to his enemy vertically 
	 */
	private void notfollowVertical() {
		if (this.enemy.getPosition().y >= this.position.y) {
			this.keyCode = this.MOVE_UP;

		}
		if (this.enemy.getPosition().y < this.position.y) {
			this.keyCode = this.MOVE_DOWN;
		}


	}
	@Override
	public void accept(IPainter painter) {
		// TODO Auto-generated method stub
		painter.paint(this);
	}

	public void pause(boolean pause)
	{
		this.pause = pause;

	}

	public void run() {

		while (this.isAlive() && this.enemy.alive) {
			while(pause == true)
			{
				this.speed = 0;
			}
			while(pause == false)
			{
				this.speed = SPEED;
				try {


					String answer;
					IBTPosition<String> n = decisionTree.root();
					while(n.getLeftChild() != null && n.getRightChild() != null)
					{
						//decision = r.nextBoolean();
						chance = (int) (Math.random() * 100);

						if(chance <= this.YES_PROBABILITY)
						{
							//if(r.nextBoolean())
							n = n.getLeftChild();

							//n.getLeftChild();
						}
						else
						{
							n = n.getRightChild();

						}
					}
					answer = n.getElement();

					//System.out.println(answer);
					//					decisionTree.addLeft(decisionTree.root().getLeftChild(), "follow horizontally");
					//					decisionTree.addRight(decisionTree.root().getLeftChild(), "follow vertically");
					//					decisionTree.addLeft(decisionTree.root().getRightChild(), "not follow horizontally");
					//					decisionTree.addRight(decisionTree.root().getRightChild(), "not follow vertically");
					switch(answer)
					{
					case "follow horizontally":
					{
						if(this.yLineOfSight())
						{
							this.keyCode = this.SHOOT;
						}
						else
							this.followPlayerHorizontal();
						break;
					}
					case "follow vertically":
					{
						if(this.xLineOfSight())
						{
							this.keyCode = this.SHOOT;

						}
						else
							this.followPlayerVertical();
						break;
					}
					case "not follow horizontally":
					{
						if(this.yLineOfSight())
						{
							this.keyCode = this.SHOOT;

						}
						else
							this.notfollowHorizontal();
						break;
					}
					case "not follow vertically":
					{
						if(this.xLineOfSight())
						{
							this.keyCode = this.SHOOT;

						}
						else
							this.notfollowVertical();
						break;
					}
					}

					//this.shoot();



					//chance = (int) (Math.random() * 100);
					//System.out.println(pause);
					// The probability of the tank following the player or not
					//					if (chance <= this.YES_PROBABILITY) {
					//						chance = (int) (Math.random() * 100); 
					//						this.followPlayer(chance);
					//
					//					} else {
					//						chance = (int) (Math.random() * 100);
					//						this.notfollowPlayer(chance);
					//					}

					// if (this.enemy.getPosition().y > this.position.y) {
					// this.keyCode = this.MOVE_DOWN;
					//
					// }
					// if (this.enemy.getPosition().y < this.position.y) {
					// this.keyCode = this.MOVE_UP;
					// }
					// } else {
					// if (this.enemy.getPosition().x > this.position.x) {
					//
					// this.keyCode = this.MOVE_RIGHT;
					// }
					// if (this.enemy.getPosition().x < this.position.x) {
					// this.keyCode = this.MOVE_LEFT;
					// }
					// }
					// if (chance < 25) {
					// this.moveUp();
					// } else if (chance < 50) {
					// this.moveDown();
					// } else if (chance < 75) {
					// this.moveLeft();
					// } else if (chance < 100) {
					// this.moveRight();
					//
					// }

					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// if (chance < 25) {
		// this.moveUp();
		// } else if (chance < 50) {
		// this.moveDown();
		// } else if (chance < 75) {
		// this.moveLeft();
		// } else if (chance < 100) {
		// this.moveRight();
		// }


		//Depending on the KeyCode the tank will move in a certain way
		if (this.keyCode == this.MOVE_DOWN) {
			this.moveDown();
			//System.out.println("down");
		}

		if (this.keyCode == this.MOVE_UP) {
			this.moveUp();
			//System.out.println("up");
		}

		if (this.keyCode == this.MOVE_LEFT) {
			this.moveLeft();
			//System.out.println("left");
		}

		if (this.keyCode == this.MOVE_RIGHT) {
			this.moveRight();
			//	System.out.println("right");
		}
		if (this.keyCode == this.SHOOT)
		{
			this.shoot();
			System.out.println("shoot");
		}
		if(!this.isAlive() || !(this.enemy.isAlive()))
		{
			t.stop();
		}
		//if(this.enemy.isAlive())
	}



}
