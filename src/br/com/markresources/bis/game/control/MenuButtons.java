package br.com.markresources.bis.game.control;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;

import android.util.Log;
import br.com.markresources.bis.game.scenes.GameScene;
import br.com.markresources.bis.util.Assets;
import br.com.markresources.bis.util.DeviceSettings;
import br.com.markresources.bis.util.SoundUtil;

public class MenuButtons extends CCLayer implements ButtonDelegate {

	private Button playButton;
	private Button highscoredButton;
	private Button helpButton;
	private Button soundButton;

	public MenuButtons() {
		this.setIsTouchEnabled(true);

		this.playButton = new Button(Assets.PLAY);
		this.highscoredButton = new Button(Assets.HIGHSCORE);
		this.helpButton = new Button(Assets.HELP);
//		this.soundButton = new Button(Assets.SOUND);
		
		this.playButton.setDelegate(this);
	    this.highscoredButton.setDelegate(this);
	    this.helpButton.setDelegate(this);
//	    this.soundButton.setDelegate(this);

		setButtonspPosition();

		addChild(playButton);
		addChild(highscoredButton);
		addChild(helpButton);
//		addChild(soundButton);

	}

	private void setButtonspPosition() {
		// Buttons Positions
		playButton.setPosition(
				DeviceSettings.screenResolution(
						CGPoint.ccp( DeviceSettings.screenWidth() /2 , DeviceSettings.screenHeight() /2 ))
				);
		highscoredButton.setPosition(
				DeviceSettings.screenResolution(
						CGPoint.ccp( DeviceSettings.screenWidth() /2 , DeviceSettings.screenHeight() /2 - 50 ))
				);
		helpButton.setPosition(
				DeviceSettings.screenResolution(
						CGPoint.ccp( DeviceSettings.screenWidth() /2 , DeviceSettings.screenHeight() /2 - 100 ))
				);
//		soundButton.setPosition(
//				DeviceSettings.screenResolution(
//						CGPoint.ccp( DeviceSettings.screenWidth() /2 - 100,
//								DeviceSettings.screenHeight() - 420 )));
	}

	@Override
	public void buttonClicked(Button sender) {
		Log.i("TESTE", "TESTE");
		if (sender.equals(this.playButton)) {
			System.out.println("Button clicked: Play");
			CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(1, GameScene.createGame()));
		}
		if (sender.equals(this.highscoredButton)) {
			System.out.println("Button clicked: Highscore");
		}
		if (sender.equals(this.helpButton)) {
			System.out.println("Button clicked: Help");
		}
//		if (sender.equals(this.soundButton)) {
//			SoundUtil.stopStartSound();
//		}
	}

}
