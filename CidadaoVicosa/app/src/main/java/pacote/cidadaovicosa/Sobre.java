package pacote.cidadaovicosa;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.internal.fi;

import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Sobre extends Activity {

	public static final  int FECHAR  = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_sobre);
		
		TextView appVersion = (TextView) findViewById(R.id.versaoSobre);
		PackageInfo pInfo;
		
		//OBTEM A VERSÃO DO APLICATIVO
		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			String version = pInfo.versionName;
			appVersion.setText("Versão " + version);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}	
		
		ImageView img = (ImageView) findViewById(R.id.fundo_texto_sobre);
		
		//ANIMAÇÃO ALPHA PARA FUNDO DO TEXTO NA API LEVEL 8
		AlphaAnimation alpha = new AlphaAnimation(0.8F, 0.8F);
		alpha.setDuration(0); // Make animation instant
		alpha.setFillAfter(true); // Tell it to persist after the animation ends
		img.startAnimation(alpha);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super .onCreateOptionsMenu(menu);
			
		MenuItem item = menu.add(0, FECHAR, 1, "Voltar");

		return true;
	}
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId())  {
			case FECHAR:
				finish();
				return  true;

		}
		
		return  false;
	}
	
}
