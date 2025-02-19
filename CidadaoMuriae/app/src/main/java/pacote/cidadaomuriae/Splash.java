package pacote.cidadaomuriae;


import com.google.analytics.tracking.android.EasyTracker;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class Splash extends Activity {
	
	private static int SPLASH_TIME_OUT = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_splash);
		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				
				Intent i = new Intent(getApplicationContext(),Colaboracao.class);
				startActivity(i);
				finish();
				
			}
		}, SPLASH_TIME_OUT);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		//Google Analytics
		EasyTracker.getInstance(this).activityStart(this);
	}
	
	@Override
	public void onStop() {
	    super.onStop();
	   
	    //Google Analytics
	    EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	}


}
