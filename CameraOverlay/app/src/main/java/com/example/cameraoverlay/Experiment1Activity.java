package com.example.cameraoverlay;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
 
public class Experiment1Activity extends Activity {
 
    //private Camera mCamera;
    private CameraPreview mPreview;
 
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        // Hide the window title.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
 
        setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
 
 
        // Create an instance of Camera
        //mCamera = getCameraInstance();
 
        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.camera_preview);
        frameLayout.addView(mPreview);
 
        RelativeLayout relativeLayoutControls = (RelativeLayout) findViewById(R.id.controls_layout);
        relativeLayoutControls.bringToFront();
 
        RelativeLayout relativeLayoutSensorsData = (RelativeLayout) findViewById(R.id.sensors_data_layout);
        relativeLayoutSensorsData.bringToFront();
 
    }
 
    public void logSensorData(View view)
    {
    	Toast.makeText(getApplicationContext(), "Logged", Toast.LENGTH_SHORT).show();
    }
 
    @Override
    protected void onPause()
    {
    	super.onPause();
    	//mPreview.releaseCamera();
    	//releaseCamera();
    	
    }
    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
 
   /* private void releaseCamera(){
        if (mCamera != null){
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }*/
 
}
