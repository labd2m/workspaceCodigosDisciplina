package br.livro.android.cap8.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.widget.Toast;

/**
 * Exemplo de como reveber o broadcast de uma Intent
 * 
 * @author ricardo
 *
 */
public class ExemploReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context c, Intent intent) {
	
		Toast.makeText(c, "Teste BroadcastReceiver !!!", Toast.LENGTH_SHORT).show();
	}
}
