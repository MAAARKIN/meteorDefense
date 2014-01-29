package br.com.markresources.bis.util;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.sound.SoundEngine;

import br.com.markresources.bis.R;

public class SoundUtil extends SoundEngine {
	
	public SoundUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static void playEffect(int resIdSound) {
		sharedEngine().playEffect(CCDirector.sharedDirector().getActivity(), resIdSound);
	}
	
	
	public static void playSound(int resIdSound, boolean loop) {
		sharedEngine().playSound(CCDirector.sharedDirector().getActivity(), resIdSound, loop);
	}

}
