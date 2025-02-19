package com.example.contadadordefaltas;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class TirarFoto extends Activity {
  public final static String DEBUG_TAG = "TirarFoto";
  private Camera camera;
  private int cameraId = 0;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.control);

    // do we have a camera?
    if (!getPackageManager()
        .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
      Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG)
          .show();
    } else {
      cameraId = findFrontFacingCamera();
     // camera = Camera.open(cameraId);
      Toast.makeText(this, "ID DA CAMERA: " + cameraId,
              Toast.LENGTH_LONG).show();
      if (cameraId < 0) {
        Toast.makeText(this, "No front facing camera found.",
            Toast.LENGTH_LONG).show();
      } else {
        camera = Camera.open(cameraId);
      }
    }
  }

  public void onClick(View view) {
    camera.takePicture(null, null,
        new PhotoHandler(getApplicationContext()));
  }

  private int findFrontFacingCamera() {
    int cameraId = -1;
    // Search for the front facing camera
    int numberOfCameras = Camera.getNumberOfCameras();
    for (int i = 0; i < numberOfCameras; i++) {
      CameraInfo info = new CameraInfo();
      Camera.getCameraInfo(i, info);
      if (info.facing == CameraInfo.CAMERA_FACING_BACK) {
        Log.d(DEBUG_TAG, "Camera found");
        cameraId = i;
        break;
      }
    }
    return cameraId;
  }

  @Override
  protected void onPause() {
    if (camera != null) {
      camera.release();
      camera = null;
    }
    super.onPause();
  }

} 
