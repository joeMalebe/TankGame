/**
 * 
 */
package DesignPatterns;

import Tanks.Bullet;
import Tanks.PlayerTank;
import WorldObjects.Brick;
import WorldObjects.Road;
import WorldObjects.Stone;
import WorldObjects.Water;
import Tanks.*;

/**
 * @author General
 *
 */
public interface IPainter {
	public void paint(PlayerTank player);
	public void paint(Enemy enemy);
	public void paint(Bullet bullet);
	public void paint(Brick brick);
	public void paint(Stone stone);
	public void paint(Water water);
	public void paint(Road road);
}
