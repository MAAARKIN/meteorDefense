package br.com.meteordefense.util;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import android.hardware.SensorManager;

public class DeviceSettings {
	
	private static SensorManager sensorManager;

	public DeviceSettings() {
		// TODO Auto-generated constructor stub
	}

	public static CGPoint screenResolution(CGPoint gcPoint) {
		return gcPoint;
	}

	public static CGSize winSize() {
		return CCDirector.sharedDirector().winSize();
	}
	
	public static float screenWidth() {
		return winSize().width;
	}

	public static float screenHeight() {
		return winSize().height;
	}

	public static SensorManager getSensorManager() {
		return sensorManager;
	}

	public static void setSensorManager(SensorManager sensorManager) {
		DeviceSettings.sensorManager = sensorManager;
	}
}
