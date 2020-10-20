package com.example.contadadordefaltas;

import java.io.IOException;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

public class Preview extends Activity {    
    private CameraPreview mPreview;
    SurfaceView surfaceView;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_captura);
        
        // Hide the window title.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this);
        //LinearLayout t = (LinearLayout) findViewById(R.id.tela);
        //t.addView(mPreview);
        setContentView(mPreview);
    }
    
    public void comecarCamera(View v){

   }
   
   public void pararCamera(View v){

   }
  

}