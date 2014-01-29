package br.com.markresources.bis.game.engines;

import java.lang.reflect.Method;
import java.util.List;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import br.com.markresources.bis.game.interfaces.CollisionEngineDelegate;
import br.com.markresources.bis.game.model.Meteor;
import br.com.markresources.bis.game.model.Player;
import br.com.markresources.bis.game.model.Shoot;
import br.com.markresources.bis.game.scenes.GameOverScreen;
import br.com.markresources.bis.game.scenes.GameScene;

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
			CGRect rect1 = getBoarders(array1.get(i));
			for (int j = 0; j < array2.size(); j++) {
				// Pega objeto do segundo array
				CGRect rect2 = getBoarders(array2.get(j));
				// Verifica colisão, faz uma interseção entre as areas dos sprites obtidas.
				if (CGRect.intersects(rect1, rect2)) {
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
