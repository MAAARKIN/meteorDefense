package br.com.meteordefense.game.interfaces;

import br.com.meteordefense.game.model.Meteor;

public interface MeteorsEngineDelegate {
	public void createMeteor(Meteor meteor);
	public void removeMeteor(Meteor meteor);
}
