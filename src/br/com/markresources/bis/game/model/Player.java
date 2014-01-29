package br.com.markresources.bis.game.model;

import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import br.com.markresources.bis.R;
import br.com.markresources.bis.accelerometer.Accelerometer;
import br.com.markresources.bis.game.interfaces.AccelerometerDelegate;
import br.com.markresources.bis.game.interfaces.ShootEngineDelegate;
import br.com.markresources.bis.game.scenes.GameScene;
import br.com.markresources.bis.util.Assets;
import br.com.markresources.bis.util.DeviceSettings;
import br.com.markresources.bis.util.Runner;
import br.com.markresources.bis.util.SoundUtil;

public class Player extends CCSprite implements AccelerometerDelegate {
	
	private final int LIMITE_DO_MAPA = 30;
	private final int VELOCIDADE_MOVIMENTO_NAVE = 10;
	private final int NOISE = 2;
	private Shoot genericShoot;
	private Accelerometer accelerometer;
	private GameScene gameScene;
	
	private float positionX, positionY;
	private float currentAccelX, currentAccelY;
    
	private ShootEngineDelegate delegate;
	
	public Player() {
		super(Assets.NAVE);
		setPositionX(DeviceSettings.screenWidth() / 2);
		setPositionY(100);
		setPosition(getPositionX(), getPositionY());
		this.schedule("update");
	}
	
	public static Player iniciarPlayer() {
		return new Player();
	}
	
	public void update(float dt) {
		if(Runner.check().isGamePlaying()) {
			if(this.currentAccelX < -NOISE){
				//nao permite ultrapassar o limite da tela para o lado direito do mapa
				if(getPositionX() < DeviceSettings.screenWidth() - LIMITE_DO_MAPA){
					this.positionX++;				
				}
			}
			
			if(this.currentAccelX > NOISE){
				if(getPositionX() > LIMITE_DO_MAPA) {
					this.positionX--;				
				}
			}
			if(this.currentAccelY < -NOISE){
				if(getPositionY() < DeviceSettings.screenHeight() - LIMITE_DO_MAPA) {
					this.positionY++;				
				}
			}
			if(this.currentAccelY > NOISE){
				if(getPositionY() > LIMITE_DO_MAPA) {
					this.positionY--;				
				}
			}
			// Configura posicao do aviao
			this.setPosition(CGPoint.ccp(this.getPositionX(), this.getPositionY()));
		}
	}
	
	public void setDelegate(ShootEngineDelegate delegate) {
		this.delegate = delegate;
	}
	
	public void catchAccelerometer() {
		//Accelerometer.sharedAccelerometer().catchAccelerometer();
	    this.accelerometer = Accelerometer.sharedAccelerometer();
	    this.accelerometer.setDelegate(this);
	}

	public void shoot(Shoot shoot) {
		shoot.positionShoot(getPositionX(), getPositionY() + 30);
		delegate.createShoot(shoot);			

	}
	
	/**
	 * move a nave para a esquerda.
	 */
	public void moveLeft() {
		//nao permite que a nave ultrapasse a margem esquerda de 30
		if(Runner.check().isGamePlaying()) {
			if(getPositionX() > LIMITE_DO_MAPA){
				setPositionX(getPositionX() - VELOCIDADE_MOVIMENTO_NAVE);
			}
			setPosition(getPositionX(), getPositionY());						
		}
	}
	
	/**
	 * move a nave para a direita.
	 */
	public void moveRight() {
		if(Runner.check().isGamePlaying()) {
			if(getPositionX() < DeviceSettings.screenWidth() - LIMITE_DO_MAPA){
				setPositionX(getPositionX() + VELOCIDADE_MOVIMENTO_NAVE);
			}
			setPosition(getPositionX(), getPositionY());						
		}
	}
	
	public void explode() {
		SoundUtil.playEffect(R.raw.over);
		SoundUtil.sharedEngine().pauseSound();
		//para o agendamento
		this.unschedule("update");
		
		//cria efeitos
		float dt = 0.2f;
		CCScaleBy effectScale = CCScaleBy.action(dt, 2f);
		CCFadeOut effectFade = CCFadeOut.action(dt);
		CCSpawn effectSpawn = CCSpawn.actions(effectScale, effectFade);
//		CCCallFunc func = CCCallFunc.action(this, "removeMe");
		
		this.runAction(CCSequence.actions(effectSpawn));
		this.removeFromParentAndCleanup(true);
	}
	
	@SuppressWarnings("unused")
	private void removeMe() {
		this.removeFromParentAndCleanup(true);
	}

	public Shoot getGenericShoot() {
		return genericShoot;
	}

	public void setGenericShoot(Shoot genericShoot) {
		this.genericShoot = genericShoot;
	}

	@Override
	public void accelerometerDidAccelerate(float x, float y) {
		System.out.println("Eixo X: " + x);
		System.out.println("Eixo Y: " + y);
		
		this.currentAccelX = x;
		this.currentAccelY = y;
	}

	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}
	
	

}
