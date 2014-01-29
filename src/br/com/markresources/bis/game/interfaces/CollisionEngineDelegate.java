package br.com.markresources.bis.game.interfaces;

import java.util.List;

import org.cocos2d.nodes.CCSprite;

public interface CollisionEngineDelegate {

	public boolean checkRadiusHitsOfArray(List<? extends CCSprite> array1, 
			List<? extends CCSprite> array2, String hit);
}
