package br.com.meteordefense.util;

import br.com.meteordefense.game.sprite.LaserShoot;
import br.com.meteordefense.game.sprite.Shoot;

/**
 * Created by Marquinhos on 24/12/13.
 */
public class MunitionFactory {

	public static final int SHOOT = 1;
	public static final int LASER_SHOOT = 2;

    public static Shoot instanceOfShoot(int valor) {
        if(valor == SHOOT) {
        	return new Shoot();
        } else if(valor == LASER_SHOOT) {
        	return new LaserShoot();
        } else {
        	return new Shoot();
        }
    }

}
