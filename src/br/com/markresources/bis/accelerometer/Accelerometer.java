package br.com.markresources.bis.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import br.com.markresources.bis.game.interfaces.AccelerometerDelegate;
import br.com.markresources.bis.util.DeviceSettings;

public class Accelerometer implements SensorEventListener {
	
	private final int LOOPACCEL = 70;
	
	private float currentAccelerationX;
	private float currentAccelerationY;
	
	private float calibratedAccelerationX;
	private float calibratedAccelerationY;
	
	private int calibrated;
	
	private static Accelerometer sharedAccelerometer = null;
	private AccelerometerDelegate delegate;
	private SensorManager sensorManager;
	
	private Accelerometer() {
	    this.catchAccelerometer();
	}
	
	public static Accelerometer sharedAccelerometer() {
	    if (sharedAccelerometer == null) {
	        sharedAccelerometer = new Accelerometer();
	    }
	    return sharedAccelerometer;
	}
	
	public void catchAccelerometer() {
	    sensorManager = DeviceSettings.getSensorManager();
	    sensorManager.registerListener(this,
	            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	            SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if(calibrated < LOOPACCEL) {
			this.calibratedAccelerationX += event.values[0];
			this.calibratedAccelerationY += event.values[1];
			
			System.out.println(event.values[0]);
	        System.out.println(event.values[1]);
	        
	        calibrated++;
	        
	        if (calibrated == LOOPACCEL ) {
	        	this.calibratedAccelerationX /= LOOPACCEL;
	        	this.calibratedAccelerationY /= LOOPACCEL;
	        }
	        return;
		}
		
		// Leitura da aceleracao
		this.currentAccelerationX = event.values[0] - this.calibratedAccelerationX;
		this.currentAccelerationY = event.values[1] - this.calibratedAccelerationY;
		
//		this.currentAccelerationX = event.values[0]; //eixo X;
//		this.currentAccelerationY = event.values[1]; //eixo Y;

		if (this.delegate != null) {
			this.delegate.accelerometerDidAccelerate(currentAccelerationX, currentAccelerationY);
		}
	}

	public AccelerometerDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(AccelerometerDelegate delegate) {
		this.delegate = delegate;
	}

}
