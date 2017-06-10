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
import DesignPatterns.IContext;
import Graphics.GameDrawer;
import Graphics.GamePainter;
import Tanks.Enemy;



public	class GamePlayGfx extends Screen implements ActionListener, KeyListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2187452022925538591L;
		private GamePainter gp;
		private GameDrawer gd;
		private IContext wrapper;
		private PlayerTank player;
		private Timer t;
		private Queue<Enemy> enemies;
		private Queue<List<Enemy>> heat;
		public static final int GAME_PLAY_WIDTH = GameFrame.FRAME_WIDTH - 100;
		public static final int GAME_PLAY_HEIGHT = GameFrame.FRAME_HEIGHT;
		private int keyCode;
		Bullet b;
		private Point[] spawnPosition;
		public long count;
		boolean alreadyRan;
		boolean win;
		BufferedImage background = null;
		/**
		 * @param args
		 */
		public GamePlayGfx(IContext wrapper)
		{
			super(wrapper);
			this.wrapper = wrapper;
			t = new Timer(5,this);
			heat = new Queue<List<Enemy>>();
			this.enemies = new Queue<Enemy>();

			win = false;
			spawnPosition = new Point[3];
			spawnPosition[0] = new Point(GamePlayGfx.GAME_PLAY_WIDTH - 100,0);
			spawnPosition[1] = new Point(GamePlayGfx.GAME_PLAY_WIDTH - 100,(GamePlayGfx.GAME_PLAY_HEIGHT/2));
			spawnPosition[2] = new Point(GamePlayGfx.GAME_PLAY_WIDTH - 100,GamePlayGfx.GAME_PLAY_HEIGHT - 100);
			this.addKeyListener(this);
			//player = new PlayerTank(thi);
			gp = new GamePainter();
			
			//b = new Bullet();
			try {
				background = ImageIO.read(new File("./data/tileset_ground.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			count = 0;

			new List<Tank>();
			List<Enemy> friends = new List<Enemy>();
			heat.enqueue(friends);
			gd = new GameDrawer( player, b, this);



			this.setSize(GamePlayGfx.GAME_PLAY_WIDTH,GamePlayGfx.GAME_PLAY_HEIGHT);		//new Thread(enemy).start();

			t.start();

			alreadyRan = false;
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
			g.drawString("Kills: " + GamePlayGfx.this.player.kills, (GamePlayGfx.GAME_PLAY_WIDTH + 10), 50);
			

		}



		public void actionPerformed(ActionEvent e) {

			
			this.gd.setKeyCode(keyCode);
			
			this.repaint();
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

		class Score extends JPanel
		{
			@Override
			public void paintComponent(Graphics g)
			{
				g.setColor(Color.black);
				g.drawString("Kills: " + GamePlayGfx.this.player.kills, GamePlayGfx.GAME_PLAY_WIDTH + 10, 50);
			}
		}

		@Override
		public void goNext() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void handle() {
			// TODO Auto-generated method stub
			
		}

		public void gameOver() {
			t.stop();
			GamePlayGfx.this.wrapper.setState(new GameOverScreen(wrapper));

		}

	}
