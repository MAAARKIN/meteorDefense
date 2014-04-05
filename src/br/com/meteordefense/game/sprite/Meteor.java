package br.com.meteordefense.game.sprite;

import java.util.Random;

import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import br.com.markresources.bis.R;
import br.com.meteordefense.game.interfaces.MeteorsEngineDelegate;
import br.com.meteordefense.util.DeviceSettings;
import br.com.meteordefense.util.Runner;
import br.com.meteordefense.util.SoundUtil;

public class Meteor extends CCSprite {
	
	private MeteorsEngineDelegate delegate;
	
	private float x, y;	
	
	public Meteor(String image) {
		super(image);
		x = new Random().nextInt(Math.round(DeviceSettings.screenWidth()));
		y = DeviceSettings.screenHeight();
	}
	
	public void start() {
	    this.schedule("update");
	}
	
	public void update(float dt) {
		if(Runner.check().isGamePlaying()) {
			y -= 1;
			this.setPosition(CGPoint.ccp(x, y ));						
		}
	}
	
	public void shooted() {
		SoundUtil.playEffect(R.raw.bang);
		
		this.delegate.removeMeteor(this);
		this.unschedule("update");
		
		float dt = 0.2f;
		CCScaleBy effectScale = CCScaleBy.action(dt, 0.5f);
		CCFadeOut effectFade = CCFadeOut.action(dt);
		CCSpawn effectSpawn = CCSpawn.actions(effectScale, effectFade);
//		CCCallFunc func = CCCallFunc.action(this, "removeMe");
//		this.runAction(CCSequence.actions(effectSpawn, func));
		this.runAction(CCSequence.actions(effectSpawn));
	}
	
	private void removeMe() {
		this.removeFromParentAndCleanup(true);
	}

	public MeteorsEngineDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(MeteorsEngineDelegate delegate) {
		this.delegate = delegate;
	}
}
