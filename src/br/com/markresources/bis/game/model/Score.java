package br.com.markresources.bis.game.model;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.opengl.CCBitmapFontAtlas;

import br.com.markresources.bis.game.scenes.GameScene;
import br.com.markresources.bis.util.DeviceSettings;

public class Score extends CCLayer {

	private int score;
	private CCBitmapFontAtlas text;
	private GameScene gameScene;
	
	public Score(GameScene gameScene) {
		this.gameScene = gameScene;
		this.score = 0;
		this.text = CCBitmapFontAtlas.bitmapFontAtlas(String.valueOf(this.score), "UniSansSemiBold_Numbers_240.fnt");
		this.text.setScale(0.5f); //define a escala que os numeros aparecerão na tela.
		this.setPosition(DeviceSettings.screenWidth()-50, DeviceSettings.screenHeight()-50);
        this.addChild(this.text);
	}
	
	public void increase() {
		score++;
		this.text.setString(String.valueOf(score));
		if(score == 5) {
			this.gameScene.startFinalScreen();
		}
	}
	
}
