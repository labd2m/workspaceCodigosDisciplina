package br.livro.android.cap7.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Exemplo de menu
 * 
 * @author ricardo
 * 
 */
public class ExemploMenu extends Activity {
	public static final int NOVO = 0;
	public static final int SALVAR = 1;
	public static final int EXCLUIR = 2;
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		TextView view = new TextView(this);
		view.setText("Exemplo de Menu");
		setContentView(view);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Adiciona três opções no menu
		MenuItem item = menu.add(0, NOVO, 1, "Novo");
		item.setIcon(br.livro.android.cap7.R.drawable.novo);
		item = menu.add(0, SALVAR, 2, "Salvar");
		item.setIcon(br.livro.android.cap7.R.drawable.salvar);
		item = menu.add(0, EXCLUIR, 3, "Excluir");
		item.setIcon(br.livro.android.cap7.R.drawable.excluir);
		return true;
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case NOVO:
			Toast.makeText(this, "Novo!", Toast.LENGTH_SHORT).show();
			return true;
		case SALVAR:
			Toast.makeText(this, "Salvar!", Toast.LENGTH_SHORT).show();
			return true;
		case EXCLUIR:
			Toast.makeText(this, "Excluir!", Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}
}
