package UI;

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
import WorldObjects.World;
import DesignPatterns.IContext;
import DesignPatterns.IState;
import Graphics.GamePainter;
import Tanks.Enemy;



public	class GamePlayScreen extends Screen implements IState, ActionListener, KeyListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2187452022925538591L;
		private GamePainter gp;
		private IContext wrapper;
		private Enemy enemy;
		private Enemy e1;
		private int counter;
		private int nTanks;
		private int spawnPos;
		private PlayerTank player;
		private Timer t;
		private Enemy e2;
		private int l = 0;
		private Queue<Enemy> enemies;
		private Queue<List<Enemy>> heat;
		public static final int GAME_PLAY_WIDTH = GameFrame.FRAME_WIDTH - 100;
		public static final int GAME_PLAY_HEIGHT = GameFrame.FRAME_HEIGHT;
		private boolean pause;
		private int keyCode;
		Bullet b;
		private Point[] spawnPosition;
		public long count;
		private World world;
		boolean alreadyRan;
		boolean win;
		BufferedImage background = null;
		/**
		 * @param args
		 */
		public GamePlayScreen(IContext wrapper,World world)
		{
			super(wrapper);
			this.wrapper = wrapper;
			this.world = world;
			t = new Timer(5,this);
			heat = new Queue<List<Enemy>>();
			this.enemies = new Queue<Enemy>();
			
			win = false;
			spawnPosition = new Point[3];
			spawnPosition[0] = new Point(GamePlayScreen.GAME_PLAY_WIDTH - 100,0);
			spawnPosition[1] = new Point(GamePlayScreen.GAME_PLAY_WIDTH - 100,(GamePlayScreen.GAME_PLAY_HEIGHT/2));
			spawnPosition[2] = new Point(GamePlayScreen.GAME_PLAY_WIDTH - 100,GamePlayScreen.GAME_PLAY_HEIGHT - 100);
			this.addKeyListener(this);
			player = new PlayerTank(world);
			//player.setWorld(world);
			gp = new GamePainter();
			b = new Bullet(world);
			try {
				background = ImageIO.read(new File("./data/tileset_ground.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			count = 0;

			List<Tank> enemies = new List<Tank>();
			List<Enemy> friends = new List<Enemy>();
			heat.enqueue(friends);




			this.setSize(GamePlayScreen.GAME_PLAY_WIDTH,GamePlayScreen.GAME_PLAY_HEIGHT);		//new Thread(enemy).start();

			t.start();

			this.pause = false;
			alreadyRan = false;
		}

		public void loadHeat()
		{
			nTanks += 3;
			//int n = 0;
			List<Enemy> friends = new List<Enemy>();
			for(int i = 0; i < nTanks; i++)
			{

				friends.addLast(new Enemy(this.world));


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
			//e.setWorld(world);

			for(IPosition<Tank> enemy: this.player.getEnemeies())
			{
				if(enemy.getElement().getRect().intersects(e.getRect())){
					this.spawnPos++;
					
				}
				
			}
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

		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			//if(!alreadyRan)
			//{
			this.gp.setGraphics(g);
			//alreadyRan = true;
			//}
			g.drawImage(background,0,0, GAME_PLAY_WIDTH, GAME_PLAY_HEIGHT, null);
			this.world.displayWorld(gp);


			player.accept(gp);
			for(IPosition<Tank> t:player.getEnemeies())
			{
				t.getElement().accept(gp);
				if(t.getElement().getBullet().isFired())
				{	


					t.getElement().getBullet().accept(gp);
				}

			}
			if(player.getBullet() != null &&player.getBullet().isFired())
			{	
				player.getBullet().accept(gp);
			}

			g.setColor(Color.black);
			g.drawString("Kills: " + GamePlayScreen.this.player.kills, (GamePlayScreen.GAME_PLAY_WIDTH -10), 50);
			

		}
		


		public void actionPerformed(ActionEvent e) {

			movePlayer(this.keyCode);
			
			if(win)
			{
				l++;
				this.world = new World(ReadGameLevel.gameLevel("./data/levels/level"+ l +".txt"));
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
			if(!player.isAlive())
			{
				t.stop();
				this.goNext();
				return;
			}
			
			//after all the actions have been performed then refresh the world
			player.refreshWorld(world);
			for(IPosition<Tank> t: player.getEnemeies())
			{
				t.getElement().refreshWorld(world);
			}
			this.repaint();
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

		public void keyPressed(KeyEvent e) {
			this.keyCode = e.getKeyCode();

		}

		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			this.keyCode = 0;
		}

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void goNext() {
			// TODO Auto-generated method stub
			this.getWrapper().setState((new GameOverScreen(this.getWrapper())));
			
		}

		@Override
		public void handle() {
			// TODO Auto-generated method stub
			
		}

		
	}
