package br.com.markresources.bis.game.scenes;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import br.com.markresources.bis.game.control.MenuButtons;
import br.com.markresources.bis.sprite.ScreenBackground;
import br.com.markresources.bis.util.Assets;
import br.com.markresources.bis.util.DeviceSettings;

public class TitleScreen extends CCLayer {
	
	private ScreenBackground background;

	public TitleScreen() {
		
		this.background = new ScreenBackground(Assets.BACKGROUND);
		this.background.setPosition(CGPoint.ccp(
				DeviceSettings.screenWidth() / 2.0f, 
				DeviceSettings.screenHeight() / 2.0f
				));
		
		this.addChild(this.background);
		
		//INSERINDO LOGO
		
		CCSprite title = CCSprite.sprite(Assets.LOGO);
		title.setPosition(CGPoint.ccp(DeviceSettings.screenWidth() /2 , DeviceSettings.screenHeight() - 130 ));
		
		this.addChild(title);
		
		//INSERINDO MENU
		MenuButtons menuLayer = new MenuButtons();
		this.addChild(menuLayer);
	}
	
	public CCScene scene() {
		CCScene scene = CCScene.node();
		scene.addChild(this);
		return scene;
	}
	
}