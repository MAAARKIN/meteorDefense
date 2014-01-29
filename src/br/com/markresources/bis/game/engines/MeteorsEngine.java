package br.com.markresources.bis.game.engines;

import java.util.List;
import java.util.Random;

import org.cocos2d.layers.CCLayer;

import android.util.Log;
import br.com.markresources.bis.game.interfaces.MeteorsEngineDelegate;
import br.com.markresources.bis.game.model.Meteor;
import br.com.markresources.bis.util.Assets;
import br.com.markresources.bis.util.Runner;

public class MeteorsEngine extends CCLayer implements MeteorsEngineDelegate {
	
	private CCLayer meteorsLayer;
	private List<Meteor> meteorsArray;
	
	public MeteorsEngine(CCLayer meteorsLayer, List<Meteor> meteorsArray) {
		this.meteorsLayer = meteorsLayer;
		this.meteorsArray = meteorsArray;
		this.schedule("meteorsEngine", 0.1f);
	}
	
	public void meteorsEngine(float dt) {
		//gera 1 em 30 gera um novo meteoro
		if(Runner.check().isGamePlaying()) {
			if(new Random().nextInt(30) == 0) {
				createMeteor(new Meteor(Assets.METEOR));
			}						
		}
	}

	/**
	 * metodo utilizado para criar os meteoros e inserir na camada
	 */
	@Override
	public void createMeteor(Meteor meteor) {
		System.out.println("Criou um meteoro");
		meteor.setDelegate(this);
		Log.i("Engine", "criou meteoro");
		// adiciona um novo meteoro a camada.
		meteorsLayer.addChild(meteor);
		// chama o schedule para update
		meteor.start();
		// adiciona o meteoro nos arrays
		this.meteorsArray.add(meteor);
		
	}

	@Override
	public void removeMeteor(Meteor meteor) {
		this.meteorsArray.remove(meteor);
	}
	
}
