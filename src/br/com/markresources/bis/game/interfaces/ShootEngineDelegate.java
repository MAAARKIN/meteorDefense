package br.com.markresources.bis.game.interfaces;

import br.com.markresources.bis.game.model.Shoot;


public interface ShootEngineDelegate {
	public void createShoot(Shoot shoot);
	public void removeShoot(Shoot shoot);
}
