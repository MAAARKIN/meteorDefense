package br.com.meteordefense.game.engines;

import java.lang.reflect.Method;
import java.util.List;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import br.com.meteordefense.game.interfaces.CollisionEngineDelegate;
import br.com.meteordefense.game.scenes.GameOverScreen;
import br.com.meteordefense.game.scenes.GameScene;
import br.com.meteordefense.game.sprite.Meteor;
import br.com.meteordefense.game.sprite.Player;
import br.com.meteordefense.game.sprite.Shoot;

public class CollisionEngine implements CollisionEngineDelegate {
	
	private GameScene gameScene;
	
	public CollisionEngine(GameScene gameScene) {
		this.gameScene = gameScene;
	}
	
	public void meteoroHit(CCSprite meteor, CCSprite shoot) {
	    ((Meteor) meteor).shooted();
	    ((Shoot) shoot).explode();
	    this.gameScene.getScore().increase();
	}
	
	public void playerHit(CCSprite meteor, CCSprite player) {
	    ((Meteor) meteor).shooted();
	    ((Player) player).explode();
	    gameScene.removePlayer();
	    CCDirector.sharedDirector().replaceScene(new GameOverScreen().scene());
	}

	/**
	 * Metodo responsável por verificar as colisões existentes dos objetos nos arrays.
	 */
	@Override
	public boolean checkRadiusHitsOfArray(List<? extends CCSprite> array1,
			List<? extends CCSprite> array2, String nomeDoMetodo) {
		boolean result = false;
		for (int i = 0; i < array1.size(); i++) {
			// Pega objeto do primeiro array
			CGRect rectangleSpriteArray1 = getBoarders(array1.get(i));
			for (int j = 0; j < array2.size(); j++) {
				// Pega objeto do segundo array
				CGRect rectangleSpriteArray2 = getBoarders(array2.get(j));
				// Verifica colisão, faz uma interseção entre as areas dos sprites obtidas.
				if (CGRect.intersects(rectangleSpriteArray1, rectangleSpriteArray2)) {
					System.out.println("Colision Detected: " + nomeDoMetodo);
					result = true;
					
					//usando reflection para executar os metodos de colisoes em tempo de execução
					Method method;
					try {
						method = CollisionEngine.class.getMethod(nomeDoMetodo, CCSprite.class, CCSprite.class);
						method.invoke(this, array1.get(i), array2.get(j));
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Responsável por obter a area do Sprite.
	 * 
	 * @param object
	 * @return
	 */
	public CGRect getBoarders(CCSprite object) {
		CGRect rect = object.getBoundingBox();
		CGPoint GLpoint = rect.origin;
		CGRect GLrect = CGRect.make(GLpoint.x, GLpoint.y, rect.size.width,
				rect.size.height);
		return GLrect;
	}

}
