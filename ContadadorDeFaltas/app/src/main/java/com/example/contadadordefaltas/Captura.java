package com.example.contadadordefaltas;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;

@SuppressLint({ "NewApi", "InlinedApi" })
public class Captura extends Activity implements SurfaceHolder.Callback{

 Camera camera;
 SurfaceView surfaceView;
 SurfaceHolder surfaceHolder;
 boolean previewing = true;
 LayoutInflater controlInflater = null;
 
   @Override
 public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_captura);
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
       
       getWindow().setFormat(PixelFormat.UNKNOWN);
       // Hide the window title.
       
       //requestWindowFeature(Window.FEATURE_NO_TITLE);
       
       surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
       
       surfaceHolder = surfaceView.getHolder();
       surfaceHolder.addCallback(this);
       surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);   
       
       
       /*controlInflater = LayoutInflater.from(getBaseContext());
       View viewControl = controlInflater.inflate(R.layout.control, null);
       LayoutParams layoutParamsControl = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
       this.addContentView(viewControl, layoutParamsControl);*/
   }
   
   public void comecarCamera(View v){
	    if(!previewing){
	        /*camera = Camera.open();
	        if (camera != null){
	         try {
	          camera.setPreviewDisplay(surfaceHolder);
	          camera.startPreview();
	          previewing = true;
	         } catch (IOException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	         }
	        }
	    }*/
	          
	   camera = Camera.open();
       try {
			camera.setPreviewDisplay(surfaceHolder);
			//camera.sta
			previewing = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
   }
   
   public void pararCamera(View v){
	   if(camera != null && previewing){
	        camera.stopPreview();
	        camera.release();
	        camera = null;
	        
	        previewing = false;
	    }
   }
  
   public void surfaceCreated(SurfaceHolder holder) {
       // The Surface has been created, acquire the camera and tell it where
       // to draw.
       camera = Camera.open();
       try {
			camera.setPreviewDisplay(holder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }

   public void surfaceDestroyed(SurfaceHolder holder) {
       // Surface will be destroyed when we return, so stop the preview.
       // Because the CameraDevice object is not a shared resource, it's very
       // important to release it when the activity is paused.
       camera.stopPreview();
       camera = null;
   }

   public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
       // Now that the size is known, set up the camera parameters and begin
       // the preview.
       Camera.Parameters parameters = camera.getParameters();
       parameters.setPreviewSize(w, h);
       camera.setParameters(parameters);
       camera.startPreview();
   }
}
