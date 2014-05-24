package br.com.meteordefense.game.scenes;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import br.com.markresources.bis.R;
import br.com.meteordefense.game.control.Button;
import br.com.meteordefense.game.control.ButtonDelegate;
import br.com.meteordefense.game.sprite.ScreenBackground;
import br.com.meteordefense.util.Assets;
import br.com.meteordefense.util.DeviceSettings;
import br.com.meteordefense.util.SoundUtil;

public class FinalScreen extends CCLayer implements ButtonDelegate {
	
	private ScreenBackground background;
	private Button beginButton;
	
	public FinalScreen() {
		this.background = new ScreenBackground(Assets.BACKGROUND);
		this.background.setPosition(CGPoint.ccp(
				DeviceSettings.screenWidth() / 2, 
				DeviceSettings.screenHeight() / 2));
		this.addChild(this.background);
		
		SoundUtil.playSound(R.raw.finalend, true); //tocar musica de fundo.
		
		CCSprite titleEnd = new CCSprite(Assets.FINALEND);
		titleEnd.setPosition(CGPoint.ccp(
				DeviceSettings.screenWidth() / 2, 
				DeviceSettings.screenHeight() - 130));
		this.addChild(titleEnd);
		
		this.setIsTouchEnabled(true); //permite que essa camada habilite o touch;
		this.beginButton = new Button(Assets.PLAY);
		this.beginButton.setPosition(CGPoint.ccp(
				DeviceSettings.screenWidth() / 2, 
				DeviceSettings.screenHeight() - 300));
		this.beginButton.setDelegate(this);
		this.addChild(beginButton);
	}
	
	public CCScene scene() {
		CCScene scene = CCScene.node();
		scene.addChild(this);
		return scene;
	}

	@Override
	public void buttonClicked(Button sender) {
		if(sender.equals(beginButton)) {
			SoundUtil.pauseSound();
			CCDirector.sharedDirector().replaceScene(new TitleScreen().scene());
		}
	}
}
