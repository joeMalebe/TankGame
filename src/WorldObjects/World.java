package WorldObjects;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import UI.GamePlayScreen;
import DesignPatterns.IPaintable;
import DesignPatterns.IPainter;
import Graphics.GamePainter;

public class World  {

	private char[][] level;
	private WorldObject[][] world;
	private ArrayList<WorldObject> worldObjects;
	public static final int OBJ_WIDTH_SIZE = (int)((double)GamePlayScreen.GAME_PLAY_WIDTH/20.0d);
	public static final int OBJ_HEIGHT_SIZE =(int)((double)GamePlayScreen.GAME_PLAY_HEIGHT/20.0d);
	private final int SIZE = 20;
	public World(char[][] level) {
		this.level = level;
		world = new WorldObject[SIZE][SIZE];
		this.initWorld();
	}
	public void initWorld(){

		worldObjects = new ArrayList<>();

		for(int x = 0; x < SIZE;x++)
		{
			for(int y = 0; y < SIZE; y++)
			{
				int xPos = OBJ_WIDTH_SIZE * x;
				int yPos = OBJ_HEIGHT_SIZE * y;

				if(level[x][y] == '0')
				{
					world[x][y] = new Road(new Point(xPos,yPos));
				}
				if(level[x][y] == '1')
				{
					world[x][y] = new Brick(new Point(xPos,yPos));
				}
				if(level[x][y] == '2')
				{
					world[x][y] = new Stone(new Point(xPos,yPos));
				}
				if(level[x][y] == '3')
				{
					world[x][y] = new Water(new Point(xPos,yPos));
				}

				worldObjects.add(world[x][y]);

			}
		}


	}



	public ArrayList<WorldObject> getWorldObjects() {
		return worldObjects;
	}
	public void setWorldObjects(ArrayList<WorldObject> worldObjects) {
		this.worldObjects = worldObjects;
	}
	public char[][] getLevel() {
		return level;
	}
	public void setLevel(char[][] level) {
		this.level = level;
	}
	public WorldObject[][] getWorld() {
		return world;
	}
	public void setWorld(WorldObject[][] world) {
		this.world = world;
	}
	public void displayWorld(GamePainter g)
	{
		//for(int x = 0; x < SIZE;x++)
		//{
		//for(int y = 0; y < SIZE; y++)
		//{
		/*if(world[x][y] instanceof Road)
				{
					world[x][y].draw(g);
				}
				if(world[x][y] instanceof Brick)
				{
					/*if(((Brick)world[x][y]).isDestroyed())
					{
						//if the brick has been destroyed then it becomes a road
						Point p = world[x][y].getPoint(); 
						world[x][y] = new Road(p);
					}*
						world[x][y].draw(g);
				}
				if(world[x][y] instanceof Water)
				{
					world[x][y].draw(g);
				}
				if(world[x][y] instanceof Stone)
				{*/
		/*if(world[x][y] instanceof Brick)
				{
					if(((Brick)world[x][y]).isDestroyed())
					{
						//if the brick has been destroyed then it becomes a road
						Point p = world[x][y].getPoint(); 
						world[x][y] = new Road(p);
					}

				}*/

		for(WorldObject o : this.worldObjects)
		{
			if(o instanceof Brick)
			{
				if(((Brick)o).isDestroyed())
				{
					//if the brick has been destroyed then it becomes a road
					Point p = o.getPoint(); 
					o = new Road(p);
					
				}

			}
			o.draw(g);
		}
		
		//world[x][y].draw(g);
		//	}




	}

}



