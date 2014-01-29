package br.com.markresources.bis.game.interfaces;

import br.com.markresources.bis.game.model.Meteor;

public interface MeteorsEngineDelegate {
	public void createMeteor(Meteor meteor);
	public void removeMeteor(Meteor meteor);
}
