package br.livro.android.cap7.canvas.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

/**
 * Imprime as teclas
 * 
 * @author ricardo
 *
 */
public class TeclasActivity extends Activity {

	private TextView text;

	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);

		text = new TextView(this);
		setContentView(text);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		text.setText("KeyCode: " + keyCode);
		
		if(keyCode == KeyEvent.KEYCODE_BACK){
			return super.onKeyDown(keyCode, event);
		}
		
		return true;
	}
}
