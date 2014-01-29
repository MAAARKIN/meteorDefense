package br.com.markresources.bis.game.scenes;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;

import br.com.markresources.bis.game.control.Button;
import br.com.markresources.bis.game.control.ButtonDelegate;
import br.com.markresources.bis.sprite.ScreenBackground;
import br.com.markresources.bis.util.Assets;
import br.com.markresources.bis.util.DeviceSettings;

public class GameOverScreen extends CCLayer implements ButtonDelegate {

	private ScreenBackground background;
	private CCSprite gameOver;
	private Button backToBegin;
	
	public GameOverScreen() {
		this.background = new ScreenBackground(Assets.BACKGROUND);
		this.background.setPosition(CGPoint.ccp(
				DeviceSettings.screenWidth() / 2, 
				DeviceSettings.screenHeight() / 2));
		this.addChild(this.background);
		
		this.gameOver = new CCSprite(Assets.GAMEOVER);
		this.gameOver.setPosition(CGPoint.ccp(
				DeviceSettings.screenWidth() / 2, 
				DeviceSettings.screenHeight() - 130));
		this.addChild(gameOver);

		//habilitando o touch da camada.
		this.setIsTouchEnabled(true);
		//iniciando o button dessa tela
		this.backToBegin = new Button(Assets.PLAY);
		this.backToBegin.setPosition(CGPoint.ccp(
				DeviceSettings.screenWidth() / 2, 
				DeviceSettings.screenHeight() - 300));
	}
	
	public CCScene scene() {
		CCScene scene = CCScene.node();
		scene.addChild(this);
		return scene;
	}

	@Override
	public void buttonClicked(Button sender) {
		if(sender.equals(this.backToBegin)) {
			SoundEngine.sharedEngine().pauseSound();
			CCDirector.sharedDirector().replaceScene(new TitleScreen().scene());
		}
	}
}
