package com.example.homealternativa;

import android.content.ComponentName;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class HomeAlternativa extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_home_alternativa);
        /*
        WebView wv = (WebView) findViewById(R.id.webView1);
        
		WebSettings webSettings = wv.getSettings();
		webSettings.setSavePassword(false);
		webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);
		
		wv.loadUrl("http://www.google.com.br");*/
		
		/*	packageName  The name of the package implementing the desired component. 
			className  The name of a class inside of the application package that will be used as the component for this Intent. */
        //Intent it = new Intent();
        //it.setClassName("com.android.launcher", "com.android.launcher2.Launcher");


        Toast.makeText(this, "Passei por aqui", Toast.LENGTH_LONG).show();

        /*Intent it = new Intent(Intent.ACTION_MAIN);
        it.setComponent(new ComponentName("com.android.launcher", "com.android.launcher2.Launcher"));


        startActivity(it);*/
        finish();
    }   
}
