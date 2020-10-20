package br.livro.android.cap7.canvas.view;

import android.app.Activity;
import android.os.Bundle;
import br.livro.android.cap7.canvas.R;

/**
 * Demonstra como utilizar um canvas customizado pelo XML
 * 
 * river_raid.xml -> exibe o -> "RiverRaid"
 * 
 * @author ricardo
 * 
 */
public class ExemploRiverRaid extends Activity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.river_raid);
	}
}