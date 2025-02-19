package br.livro.android.cap7.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
}
