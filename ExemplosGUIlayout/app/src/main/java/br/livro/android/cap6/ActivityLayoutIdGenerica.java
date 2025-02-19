package br.livro.android.cap6;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Recebe o parâmetro "layoutResId" com o id do layout definido em XML
 * 
 * Chama o setContentView(id) passando este id
 * 
 * @author ricardo
 *
 */
public class ActivityLayoutIdGenerica extends Activity {

	private static final String TAG = "ID";

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Bundle extras = getIntent().getExtras();

		if(extras != null){
			int id = extras.getInt("layoutResId");
			Log.i(TAG,"Abrir layout: " + id);

			setContentView(id);
		} else {
	
			TextView text = new TextView(this);
			text.setText("Precisa informar o id do recurso do layout");
			setContentView(text);
		}
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		
		switch(newConfig.orientation) {
	        case Configuration.ORIENTATION_LANDSCAPE:
	            Toast.makeText(this, "LANDSCAPE", Toast.LENGTH_LONG).show();
	            break;
	        case Configuration.ORIENTATION_PORTRAIT:
	        	Toast.makeText(this, "LANDSCAPE", Toast.LENGTH_LONG).show();
	            break;
		}
	}
}
