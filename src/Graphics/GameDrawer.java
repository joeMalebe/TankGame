package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import List.IList;
import List.IPosition;
import List.List;
import Queue.Queue;
import Tanks.Bullet;
import Tanks.PlayerTank;
import Tanks.Tank;
import UI.GameFrame;
import UI.GameOverScreen;
import UI.GamePlayGfx;
import UI.GamePlayScreen;
import DesignPatterns.IContext;
import Tanks.Enemy;

public class GameDrawer  implements Runnable{
	private GamePainter gp;
	private PlayerTank player;
	public static final int GAME_PLAY_WIDTH = GameFrame.FRAME_WIDTH - 100;
	public static final int GAME_PLAY_HEIGHT = GameFrame.FRAME_HEIGHT;
	Bullet b;
	private GamePlayGfx screen;

	private Enemy enemy;
	private int counter;
	private int nTanks;
	private int spawnPos;
	//private PlayerTank player;
	private Timer t;
	private Queue<Enemy> enemies;
	private Queue<List<Enemy>> heat;
	//public static final int GAME_PLAY_WIDTH = GameFrame.FRAME_WIDTH - 100;
	//public static final int GAME_PLAY_HEIGHT = GameFrame.FRAME_HEIGHT;
	private boolean pause;
	//Bullet b;
	private Point[] spawnPosition;
	public long count;
	boolean alreadyRan;
	boolean win;
	private int keyCode;



	public GameDrawer(GamePlayGfx screen) {
		super();
		heat = new Queue<List<Enemy>>();
		this.enemies = new Queue<Enemy>();

		win = false;
		spawnPosition = new Point[3];
		spawnPosition[0] = new Point(GamePlayGfx.GAME_PLAY_WIDTH - 100,0);
		spawnPosition[1] = new Point(GamePlayGfx.GAME_PLAY_WIDTH - 100,(GamePlayGfx.GAME_PLAY_HEIGHT/2));
		spawnPosition[2] = new Point(GamePlayGfx.GAME_PLAY_WIDTH - 100,GamePlayGfx.GAME_PLAY_HEIGHT - 100);
		
		//player = new PlayerTank();
		gp = new GamePainter();
		
		//b = new Bullet();
		

		count = 0;

		new List<Tank>();
		List<Enemy> friends = new List<Enemy>();
		heat.enqueue(friends);




		

		

		alreadyRan = false;
		new Thread(this).start();
	}
	
	

	public GameDrawer( PlayerTank player, Bullet b, GamePlayGfx screen) {
		// TODO Auto-generated constructor stub
		this.player = player;
		this.screen = screen;
		this.b = b;
		
	}



	public void loadHeat()
	{
		nTanks += 3;
		//int n = 0;
		List<Enemy> friends = new List<Enemy>();
		for(int i = 0; i < nTanks; i++)
		{

			//friends.addLast(new Enemy());


		}
		for(IPosition<Enemy> e:friends)
		{
			for(IPosition<Enemy> f:friends)
			{
				if(e.getElement() != f.getElement())
				{
					e.getElement().getFriends().addLast(f.getElement());
				}
			}
			this.enemies.enqueue(e.getElement());
		}
		spawn();
	}

	public void spawn()
	{
		Enemy e = enemies.dequeue();
		e.setEnemy(player);



		e.setPosition(spawnPosition[this.spawnPos]);
		this.spawnPos++;
		if(this.spawnPos > 2)
		{
			this.spawnPos = 0;
		}	


		//player.getEnemeies().addLast(e);
		player.addTarget(e);


		e.init();

	}

	public void action() {
		
		if(win)
		{
			this.loadHeat();
			win = false;
			counter = 0;

		}

		if(this.enemies.isEmpty() && this.player.getEnemeies().isEmpty())
		{
			win = true;
		}
		else
		{


			if(player.isShoot())
			{
				player.getBullet().shoot(player.getDirection(), new Point(player.getCentreX(),player.getCentreY()));
				player.setShoot(false);
			}
			for(IPosition<Tank> t: player.getEnemeies())
			{
				if(t.getElement().isShoot())
				{
					t.getElement().getBullet().shoot(t.getElement().getDirection(), new Point(t.getElement().getCentreX(),t.getElement().getCentreY()));
					t.getElement().setShoot(false);

				}
				if(t.getElement().getBullet().isFired())
				{
					t.getElement().getBullet().travel();
				}
			}
			if(player.getBullet().isFired())
			{
				player.getBullet().travel();
			}
			counter++;
			if(counter > 500)
			{
				if(!this.enemies.isEmpty())
				{
					this.spawn();
					counter = 0;

				}
			}

		}
		
	}




	/**
	 * Moves the player according to the direction pressed
	 * @param keyCode The key pressed's code
	 */
	public void movePlayer(int keyCode)
	{
		if(keyCode == KeyEvent.VK_DOWN)
		{
			this.player.moveDown();

		}

		if(keyCode == KeyEvent.VK_UP)
		{
			this.player.moveUp();
		}

		if(keyCode == KeyEvent.VK_LEFT)
		{
			this.player.moveLeft();
		}

		if(keyCode == KeyEvent.VK_RIGHT)
		{
			this.player.moveRight();
		}
		if(keyCode == KeyEvent.VK_ESCAPE)
		{
			pause = !pause;
			System.out.println(pause);
			synchronized (enemy){
				enemy.pause(pause);
			}


		}
		if(keyCode == KeyEvent.VK_SPACE)
		{

			player.shoot();
		}
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(player.isAlive())
		{
			movePlayer(this.keyCode);
			this.action();
			//this.draw();
			this.screen.repaint();
		}
		
		if(!player.isAlive())
		{
			t.stop();
			screen.gameOver();

		}

		screen.repaint();

	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}



}