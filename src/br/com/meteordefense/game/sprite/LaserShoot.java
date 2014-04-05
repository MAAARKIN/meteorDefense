package br.com.meteordefense.game.sprite;

import br.com.meteordefense.util.Assets;

public class LaserShoot extends Shoot {

	public LaserShoot(float positionX, float positionY) {
		super(Assets.LASER_SHOOT, positionX, positionY);
	}
	
	public LaserShoot() {
		super(Assets.LASER_SHOOT);
		super.setVelocityOfBullet(2);
	}
	
}
