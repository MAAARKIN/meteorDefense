package br.com.meteordefense.game.interfaces;

import br.com.meteordefense.game.sprite.Shoot;


public interface ShootEngineDelegate {
	public void createShoot(Shoot shoot);
	public void removeShoot(Shoot shoot);
}
