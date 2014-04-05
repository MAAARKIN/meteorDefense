package br.com.meteordefense.game.scenes;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor4B;

import br.com.meteordefense.game.control.Button;
import br.com.meteordefense.game.control.ButtonDelegate;
import br.com.meteordefense.game.interfaces.PauseDelegate;
import br.com.meteordefense.util.Assets;
import br.com.meteordefense.util.DeviceSettings;

public class PauseScreen extends CCLayer implements ButtonDelegate {

	private Button resumeButton;
	private Button quitButton;

	private PauseDelegate delegate;

	private CCColorLayer background;

	public PauseScreen() {
		// Enable Touch
		this.setIsTouchEnabled(true);

		// Adds background
		this.background = CCColorLayer.node(ccColor4B.ccc4(0, 0, 0, 175),
				DeviceSettings.screenWidth(),
				DeviceSettings.screenHeight());
		this.addChild(this.background);

		// logo
		CCSprite title = CCSprite.sprite(Assets.LOGO);
		title.setPosition(CGPoint.ccp( DeviceSettings.screenWidth() /2 , DeviceSettings.screenHeight() - 130 )) ;
		this.addChild(title);

		// Add Buttons
		this.resumeButton = new Button(Assets.PLAY);
		this.resumeButton.setPosition(CGPoint.ccp( DeviceSettings.screenWidth() /2 , DeviceSettings.screenHeight() - 250 )) ;
		this.resumeButton.setDelegate(this);
		
		
		this.quitButton = new Button(Assets.EXIT);
		this.quitButton.setPosition(CGPoint.ccp( DeviceSettings.screenWidth() /2 , DeviceSettings.screenHeight() - 300 )) ;
		this.quitButton.setDelegate(this);

		this.addChild(this.resumeButton);
		this.addChild(this.quitButton);

	}

	public void setDelegate(PauseDelegate delegate) {
		this.delegate = delegate;
	}


	@Override
	public void buttonClicked(Button sender) {

		// Check Resume Button touched
		if (sender.equals(this.resumeButton)) {
			this.delegate.resumeGame();
			this.removeFromParentAndCleanup(true);
		}

		// Check Quit Button touched
		if (sender.equals(this.quitButton)) {
			this.delegate.quitGame();
		}
	}
}