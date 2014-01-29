package br.com.markresources.bis.game.model;

import br.com.markresources.bis.util.Assets;

public class LaserShoot extends Shoot {

	public LaserShoot(float positionX, float positionY) {
		super(Assets.LASER_SHOOT, positionX, positionY);
	}
	
	public LaserShoot() {
		super(Assets.LASER_SHOOT);
		super.setVelocityOfBullet(2);
	}
	
}
