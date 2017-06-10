package Graphics;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import DesignPatterns.IPainter;
import Tanks.Bullet;
import Tanks.PlayerTank;
import WorldObjects.Brick;
import WorldObjects.Road;
import WorldObjects.Stone;
import WorldObjects.Water;

import Tanks.Enemy;

public class GamePainter implements IPainter {
	BufferedImage pUp = null;
	BufferedImage pDown = null;
	BufferedImage pLeft = null;
	BufferedImage pRight = null;
	
	BufferedImage e1Up = null;
	BufferedImage e1Down = null;
	BufferedImage e1Left = null;
	BufferedImage e1Right = null;
	
	BufferedImage rUp = null;
	BufferedImage rDown = null;
	BufferedImage rLeft = null;
	BufferedImage rRight = null;
	BufferedImage stoneImg = null;
	BufferedImage grassImg = null;
	BufferedImage brickImg = null;
	BufferedImage waterImg = null;
	Image waterGif = null;
	BufferedImage bomb = null;
	
	private Graphics g;
	
	public GamePainter(Graphics g)
	{
		try {
			pUp = ImageIO.read(new File("./data/Tanks/playerUp.png"));
			pDown = ImageIO.read(new File("./data/Tanks/playerDown.png"));
//			pLeft = ImageIO.read(new File("./data/Tanks/playerLeft.png"));
//			pRight = ImageIO.read(new File("./data/Tanks/playerRight.png"));
			bomb = ImageIO.read(new File("./data/Bombs/explosion.gif"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		this.g = g;
	}
	
	public GamePainter()
	{
		//all game tiles from https://opengameart.org/
		try {
			pUp = ImageIO.read(new File("./data/Tanks/playerUp.png"));
			pDown = ImageIO.read(new File("./data/Tanks/playerDown.png"));
			pLeft = ImageIO.read(new File("./data/Tanks/playerLeft.png"));
			pRight = ImageIO.read(new File("./data/Tanks/playerRight.png"));
			
			
			
			e1Up = ImageIO.read(new File("./data/Tanks/bUp.png"));
			e1Down = ImageIO.read(new File("./data/Tanks/bDown.png"));
			e1Left = ImageIO.read(new File("./data/Tanks/bLeft.png"));
			e1Right = ImageIO.read(new File("./data/Tanks/bRight.png"));
			
			rUp = ImageIO.read(new File("./data/Rockets/rUp.png"));
			rDown = ImageIO.read(new File("./data/Rockets/rDown.png"));
			rLeft = ImageIO.read(new File("./data/Rockets/rLeft.png"));
			rRight = ImageIO.read(new File("./data/Rockets/rRight.png"));
			
			//water gif from http://www.html5gamedevs.com/topic/16114-phaser-mobile-ideas-for-a-2d-tiled-water-surface-effect/
			waterGif = new ImageIcon("./data/WorldObjects/waterGif.gif").getImage();
			stoneImg = ImageIO.read(new File("./data/WorldObjects/stone.jpg"));
			grassImg = ImageIO.read(new File("./data/WorldObjects/grass.png"));
			brickImg = ImageIO.read(new File("./data/WorldObjects/brick.png"));
			waterImg = ImageIO.read(new File("./data/WorldObjects/waterGif.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//this.g = g;
	}
	
	public void setGraphics(Graphics g)
	{
		this.g = g;
	}
	public void paint(PlayerTank player) {
		// TODO Auto-generated method stub
		int x = (int)player.getPosition().getX();
		int y = (int)player.getPosition().getY();
		switch(player.getDirection())
		{
			case Up:
			{
				g.drawImage(pUp, x, y, player.getSize().width, player.getSize().height, null);
				break;
			}
			case Down:
			{
				g.drawImage(pDown, x, y, player.getSize().width, player.getSize().height, null);
				break;
			}
			case Left:
			{
				g.drawImage(pLeft, x, y, player.getSize().width, player.getSize().height, null);
				break;
			}
			case Right:
			{
				g.drawImage(pRight, x, y, player.getSize().width, player.getSize().height, null);
				break;
			}
			
			
		}
		//.setColor(Color.red);
		//g.fillRect(player.getRect().x,player.getRect().y,player.getRect().width,player.getRect().height);
		//g.drawImage(pUp,x, y, null);
		
	}
	

	
	public void paint(Enemy enemy) {
		// TODO Auto-generated method stub
		int x = (int)enemy.getPosition().getX();
		int y = (int)enemy.getPosition().getY();
		
		switch(enemy.getDirection())
		{
			case Up:
			{
				g.drawImage(e1Up, x, y, enemy.getSize().width, enemy.getSize().height, null);
				break;
			}
			case Down:
			{
				g.drawImage(e1Down, x, y, enemy.getSize().width, enemy.getSize().height, null);
				break;
			}
			case Left:
			{
				g.drawImage(e1Left, x, y, enemy.getSize().width, enemy.getSize().height, null);
				break;
			}
			case Right:
			{
				g.drawImage(e1Right, x, y, enemy.getSize().width, enemy.getSize().height, null);
				break;
			}
			
		}
		
		//g.setColor(Color.blue);
		//g.fillRect(x,y,enemy.getSize().width,enemy.getSize().height);
	}

	
	public void paint(Bullet bullet) {
		int x = (int)bullet.getPosition().getX();
		int y = (int)bullet.getPosition().getY();
		int xOffSet = 8;
		int yOffSet = 8;
		if(bullet.isFired() && !bullet.isHit())
		{
			switch(bullet.getDirection())
			{
				case Up:
				{
					g.drawImage(rUp, (x - xOffSet), (y - 35), bullet.getSize().width, bullet.getSize().height, null);
					break;
				}
				case Down:
				{
					g.drawImage(rDown, (x - xOffSet), y, bullet.getSize().width, bullet.getSize().height, null);
					break;
				}
				case Left:
				{
					g.drawImage(rLeft, (x - 35), (y - yOffSet),  bullet.getSize().height,bullet.getSize().width, null);
					break;
				}
				case Right:
				{
					g.drawImage(rRight, x, (y - yOffSet),  bullet.getSize().height,bullet.getSize().width, null);
					break;
				}
				
			}
			
			//g.setColor(Color.black);
			//g.fillOval(x,y,bullet.getSize().width,bullet.getSize().height);
		}
		/*else if(bullet.isHit())
		{
			g.drawImage(bomb, x, (y - yOffSet),  bullet.getSize().height + 40,bullet.getSize().width+40, null);
		}*/
	}

	

	@Override
	public void paint(Brick brick) {
		// TODO Auto-generated method stub
		g.setColor(Color.ORANGE);
		g.drawImage(this.brickImg,brick.getPoint().x, brick.getPoint().y, brick.getSize().width, brick.getSize().height, null);
		
	}

	@Override
	public void paint(Stone stone) {
		g.setColor(Color.gray);
		g.drawImage(this.stoneImg,stone.getPoint().x, stone.getPoint().y, stone.getSize().width, stone.getSize().height,null);
		
	}

	@Override
	public void paint(Water water) {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
		g.drawImage(this.waterGif,water.getPoint().x, water.getPoint().y, water.getSize().width, water.getSize().height,null);
		
	}

	@Override
	public void paint(Road road) {
		// TODO Auto-generated method stub
		g.setColor(Color.black);
		g.drawImage(this.grassImg,road.getPoint().x, road.getPoint().y, road.getSize().width, road.getSize().height,null);
		
	}

}
