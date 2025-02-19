package com.example.homealternativa;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

import java.util.List;

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

        Toast.makeText(this, "Passei por aqui", Toast.LENGTH_LONG).show();

        getDefaultLauncher();

        finish();
    }

    void getDefaultLauncher() {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        final List<ResolveInfo> list = ((PackageManager)getPackageManager()).queryIntentActivities(intent, 0);

        //String className = null;
        for(ResolveInfo ri : list){
            Log.d("Launcher","FOUND NATIVE LAUNCH ACTIVITY " + ri.activityInfo.packageName);
            if(ri.activityInfo.packageName.contains("com.lge") ||  ri.activityInfo.packageName.contains("android") || ri.activityInfo.packageName.contains("motorola.homescreen")  || ri.activityInfo.packageName.contains("htc.launcher") ){
                Log.d("Launcher","LAUNCHING " + ri.activityInfo.packageName);
                startSpecificActivity(ri);
                return;
            }
        }
        Log.d("Launcher","DIDN'T FIND MATCHING HOME APP");
    }

    public void startSpecificActivity(ResolveInfo launchable) {

        ActivityInfo activity=launchable.activityInfo;

        ComponentName name=new ComponentName(activity.applicationInfo.packageName, activity.name);

        Intent i=new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        i.setComponent(name);
        startActivity(i);
    }
}
