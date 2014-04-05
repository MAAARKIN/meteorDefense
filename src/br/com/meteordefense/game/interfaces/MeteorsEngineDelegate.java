package br.com.meteordefense.game.interfaces;

import br.com.meteordefense.game.sprite.Meteor;

public interface MeteorsEngineDelegate {
	public void createMeteor(Meteor meteor);
	public void removeMeteor(Meteor meteor);
}
