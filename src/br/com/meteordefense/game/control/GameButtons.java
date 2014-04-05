package br.com.meteordefense.game.control;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.types.CGPoint;

import br.com.meteordefense.game.scenes.GameScene;
import br.com.meteordefense.util.Assets;
import br.com.meteordefense.util.DeviceSettings;
import br.com.meteordefense.util.Runner;

/**
 * Classe responsável por trabalhar todos os buttons que aparecem na tela em que o jogo está executando
 * 
 * @author Marquinhos
 *
 */
public class GameButtons extends CCLayer implements ButtonDelegate {

	private Button leftControl;
    private Button rightControl;
    private Button shootButton;
    private Button pauseButton;
    
    private GameScene delegate;
    
    private GameButtons() {
    	setIsTouchEnabled(true);
    	
    	this.leftControl = new Button(Assets.LEFTCONTROL);
    	this.rightControl = new Button(Assets.RIGHTBUTTON);
    	this.shootButton = new Button(Assets.SHOOTBUTTON);
    	this.pauseButton = new Button(Assets.PAUSE);
    	
    	// Configura as delegações
    	this.leftControl.setDelegate(this);
    	this.rightControl.setDelegate(this);
    	this.shootButton.setDelegate(this);
    	this.pauseButton.setDelegate(this);
    	
    	setButtonsPosition();
    	
    	// Adiciona os botões na tela
//    	addChild(leftControl);
//    	addChild(rightControl);
    	addChild(shootButton);
    	addChild(pauseButton);
	}
    
    private void setButtonsPosition() {
        // Posição dos botões
        leftControl.setPosition(CGPoint.ccp(40, 40));
        rightControl.setPosition(CGPoint.ccp(100, 40));
        shootButton.setPosition(CGPoint.ccp(DeviceSettings.screenWidth() - 40 , 40));
        pauseButton.setPosition(CGPoint.ccp(40, DeviceSettings.screenHeight() - 30));
    }
    
    public static GameButtons gameButtons() {
        return new GameButtons();
    }
    
	@Override
	public void buttonClicked(Button sender) {
		if (sender.equals(this.leftControl)) {
//			System.out.println("Button clicked: Left");
			delegate.moveLeft();
		}
		if (sender.equals(this.rightControl)) {
//			System.out.println("Button clicked: Right");
			delegate.moveRight();
		}
		if (sender.equals(this.shootButton)) {
//			System.out.println("Button clicked: Shooting!");
			if(Runner.check().isGamePlaying()) {
				delegate.shoot();
			}
		}
		if (sender.equals(this.pauseButton)) {
			this.delegate.pauseGameAndShowLayer();
		}
	}

	public GameScene getDelegate() {
		return delegate;
	}

	public void setDelegate(GameScene delegate) {
		this.delegate = delegate;
	}

}
