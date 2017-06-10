package Tanks;

import WorldObjects.World;

public class EnemyBullet extends Bullet {

	public EnemyBullet(World world)
	{
		super(world);
	}
	
	@Override
	public void travel()
	{
		if(this.isHit(enemy.getRect()))
		{
			enemy.decreaseLife(POWER);
			fired = false;
			System.out.println("bullet hit");
			return;
		}
	}
}
