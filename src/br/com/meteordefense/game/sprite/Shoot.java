package br.com.meteordefense.game.sprite;


import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import br.com.markresources.bis.R;
import br.com.meteordefense.game.interfaces.ShootEngineDelegate;
import br.com.meteordefense.util.Assets;
import br.com.meteordefense.util.Runner;
import br.com.meteordefense.util.SoundUtil;

public class Shoot extends CCSprite {
	
//	private ShootEngineDelegate delegate;
	private float positionX, positionY;
	private float velocityOfBullet = 2;
	private ShootEngineDelegate delegate;
	
	public Shoot(float x, float y) {
		super(Assets.SHOOT);
		this.positionX = x;
		this.positionY = y;
		setPosition(this.positionX, this.positionY);
	}
	
	public Shoot(String imagem) {
		super(imagem);
	}

    public Shoot() {
        super(Assets.SHOOT);
    }
	
	public Shoot(String imagem, float positionX, float positionY) {
		super(imagem);
		this.positionX = positionX;
		this.positionY = positionY;
		setPosition(this.positionX, this.positionY);
	}
	
	public Shoot(String imagem, float positionX, float positionY, float velocity) {
		super(imagem);
		this.velocityOfBullet = velocity;
		this.positionX = positionX;
		this.positionY = positionY;
		setPosition(this.positionX, this.positionY);
	}

    public void positionShoot(float positionX, float positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		setPosition(this.positionX, this.positionY);
	}
    
    public void explode() {
    	//remove do array
    	this.getDelegate().removeShoot(this);
    	// Para o agendamento
    	this.unschedule("update");
    	// Cria efeitos
    	float dt = 0.2f;
    	CCScaleBy effectScale = CCScaleBy.action(dt, 2f);
    	CCFadeOut effectFade = CCFadeOut.action(dt);
    	CCSpawn effectSpawn = CCSpawn.actions(effectScale, effectFade);
    	// Função a ser executada após efeito
    	CCCallFunc callFunction = CCCallFunc.action(this, "removeMe");
    	// Roda efeito
    	this.runAction(CCSequence.actions(effectSpawn, callFunction));
    }
    
    public void removeMe() {
    	this.removeFromParentAndCleanup(true);
    }
	
	public void fire() {
		//iniciando o som do tiro.
		//FIXME metodos shared são singletons.
		SoundUtil.playEffect(R.raw.shoot);
		this.schedule("update");
	}
	
	public void increaseFireSpeed() {
		this.velocityOfBullet += 1;
	}
	
	public void increaseFireSpeed(float velocity) {
		this.velocityOfBullet += velocity;
	}
	
	public void update(float dt) {
		if(Runner.check().isGamePlaying()) {
			positionY += velocityOfBullet;
			this.setPosition(CGPoint.ccp(positionX, positionY));			
		}
	}
	
    public void start() {
        System.out.println("shoot moving!");
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

	public float getVelocityOfBullet() {
		return velocityOfBullet;
	}

	public void setVelocityOfBullet(float velocityOfBullet) {
		this.velocityOfBullet = velocityOfBullet;
	}

	public ShootEngineDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(ShootEngineDelegate delegate) {
		this.delegate = delegate;
	}
    
    

}
