package br.com.meteordefense.game.activity;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import br.com.meteordefense.game.scenes.TitleScreen;
import br.com.meteordefense.util.DeviceSettings;
import br.com.meteordefense.util.SoundUtil;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		//definindo orientação do landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(
	            WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//configura a tela
		CCGLSurfaceView glSurfaceView = new CCGLSurfaceView(this);
		setContentView(glSurfaceView);
		CCDirector.sharedDirector().attachInView(glSurfaceView);
		
		//configura CCDirector
		CCDirector.sharedDirector().setScreenSize(320, 480);
		
		CCScene scene = new TitleScreen().scene();
		CCDirector.sharedDirector().runWithScene(scene);
		configSensorManager();
	}
	
	@Override
	protected void onDestroy() {
		SoundUtil.sharedEngine().pauseSound();
		super.onDestroy();
	}
	
	@Override
	protected void onPause() {
		SoundUtil.sharedEngine().pauseSound();
		super.onPause();
	}
	
	private void configSensorManager() {
	    SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    DeviceSettings.setSensorManager(sensorManager);
	}

}