package br.com.meteordefense.util;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.sound.SoundEngine;

public class SoundUtil {
	
	private static boolean mute = false;
//	private static SoundUtil instance = null;
//	private SoundEngine engine;
	
//	public static SoundUtil sharedSound() {
//		if(instance == null) {
//			instance = new SoundUtil();
//		}
//		return instance;
//	}
	
	public SoundUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static void playEffect(int resIdSound) {
		SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity(), resIdSound);
	}
	
	public static void playSound(int resIdSound, boolean loop) {
		SoundEngine.sharedEngine().playSound(CCDirector.sharedDirector().getActivity(), resIdSound, loop);
	}
	
	public static void pauseSound() {
		SoundEngine.sharedEngine().pauseSound();
	}
	
	public static void mute() {
		SoundEngine.sharedEngine().setSoundVolume(0f);
		SoundEngine.sharedEngine().setEffectsVolume(0f);
		mute = true;
	}
	
	public static void preloadSound(int resIdSound) {
		SoundEngine.sharedEngine().preloadSound(CCDirector.sharedDirector().getActivity(), resIdSound);
	}
	
	public static void preloadEffect(int resIdSound) {
		SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), resIdSound);
	}
	
	public static void unmute() {
		SoundEngine.sharedEngine().setSoundVolume(1f);
		SoundEngine.sharedEngine().setEffectsVolume(1f);
		mute = false;
	}
	
	public static boolean isMute() {
		return mute;
	}
}