package lx.zv.jaguar.business.sensors;

import lx.zv.jaguar.business.menu.enums.Side;
import lx.zv.jaguar.business.sensors.interfaces.OrientationListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.util.Log;

public abstract class OrientationManager implements SensorEventListener, OrientationListener{
	

	 
	  private final SensorManager mSensorManager;
	  private final Sensor mOrientation;
	  private final Sensor mAcelerometer;
	  private final Sensor mMagneticField;
	  private static final double RAD_TO_DEG = 57.2957795;//(180/pi)
	  
		private boolean supported;
		private boolean running = false;
		
		private float[] gravity = new float[3];
		private float[] geomagnetic = new float[3];
		
		private float[] R = new float[9];
		private float[] I = new float[9];
		private float[] orientationValues = new float[3];
		
		//private double azimuth;  // azimuth
		private double pitch;     // pitch
		private double roll;      // roll
		private static Side currentSide = Side.TOP;

		
		
	  
	  
	  public OrientationManager(SensorManager sm){			
		         mSensorManager = sm;
		         mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);	
		         mAcelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		         mMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
	  }
	 
	  
	  public void registerOrientationListener(){		  
		  mSensorManager.registerListener(this, mOrientation, SensorManager.SENSOR_DELAY_NORMAL);
		  mSensorManager.registerListener(this, mAcelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		  mSensorManager.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
	  }
	  
	  public void unregisterOrientationListener(){
		  mSensorManager.unregisterListener(this);		  
	  }

	  
	  private float[] getOrientationValues(){

			SensorManager.getRotationMatrix(R, I, gravity, geomagnetic);
			SensorManager.getOrientation(R, orientationValues);
			
			return orientationValues;
		  
	  }
	  
	public Side getOrientation(){
		return currentSide;
	}


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
	
		int type = event.sensor.getType();
		boolean change = false;
		if (type == Sensor.TYPE_ACCELEROMETER){
			gravity = event.values.clone();
			change = true;
		}
		if (type == Sensor.TYPE_MAGNETIC_FIELD){
			geomagnetic = event.values.clone();	
			change = true;
		}
		
		if(change){
			float[] ov = this.getOrientationValues();
			this.onOrientationChanged(ov[1], ov[2]);
		}

	}


	@Override
	public void onOrientationChanged(double pitchRad, double rollRad) {
		
		
		//azimuth = orientationValues[0]*RAD_TO_DEG;     // azimuth
		pitch = orientationValues[1]*RAD_TO_DEG;      // pitch
		roll = orientationValues[2]*RAD_TO_DEG;          // roll
		//Side tempSide = currentSide;
    
    //degrees = (radians)* (180/pi)
   
     if (pitch < -45 && pitch > -135) {
    	
    	// onTopUp();
         // top side up
    	 currentSide = Side.TOP;
     } else if (pitch > 45 && pitch < 135) {
    	//onBottomUp();
         // bottom side up
         currentSide = Side.BOTTOM;
     } else if (roll > 45) {
    	//onRightUp();
         // right side up
         currentSide = Side.RIGHT;
     } else if (roll < -45) {
    	 //onLeftUp();
         // left side up
    	 currentSide = Side.LEFT;
     }
     
     /*if(tempSide  != currentSide ){
    	 Log.d("HOLA", currentSide.toString());
     }
     */
     
		
	}


	@Override
	public abstract void onTopUp();


	@Override
	public abstract void onBottomUp();

	@Override
	public abstract void onLeftUp();


	@Override
	public abstract void onRightUp();
	  
	
	 

}
