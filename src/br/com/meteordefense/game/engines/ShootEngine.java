package br.com.meteordefense.game.engines;

import java.util.List;

import org.cocos2d.layers.CCLayer;

import br.com.meteordefense.game.interfaces.ShootEngineDelegate;
import br.com.meteordefense.game.sprite.Shoot;

public class ShootEngine extends CCLayer implements ShootEngineDelegate {

	private CCLayer shootsLayer;
	private List<Shoot> shootsArray;
	
	public ShootEngine(CCLayer shootsLayer, List<Shoot> shootsArray) {
		this.shootsLayer = shootsLayer;
		this.shootsArray = shootsArray;
	}
	
	public void createShoot(Shoot shoot) {
        shoot.fire();
        shoot.setDelegate(this);
        this.shootsLayer.addChild(shoot);
//	    shoot.setDelegate(this);
	    shoot.start();
	    this.shootsArray.add(shoot);
	}

	@Override
	public void removeShoot(Shoot shoot) {
		this.shootsArray.remove(shoot);
	}

}
