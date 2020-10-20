package br.livro.android.cap7.canvas;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

/**
 * 
 * 
 * @author ricardo
 *
 */
public class CanvasTeclasActivity extends Activity {

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
		
		return true;
	}
}
