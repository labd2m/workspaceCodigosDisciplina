package br.livro.android.cap8;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos de Intents para abrir o browser, fazer ligações, etc
 * 
 * @author rlecheta
 * 
 */
public class Menu extends ListActivity {
	
	private static final String[] nomes = new String[] {
			"Abrir tela 1 ou 2 - ação e categoria DEFAULT",
			"Abrir tela 2 - ação + categoria",
			"Abrir tela 3 - ação + categoria",
			"Abrir tela 4 ou 5 - ação + categoria",
			"Abrir BroadcastReceiver1",
			"Sair"};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nomes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		Intent it;

		switch (position) {
			case 0:
				//Abrir a tela 1 ou 2
				it = new Intent("ACAO_TESTE");
				startActivity(it);
				break;
			case 1:
				//Abrir a tela 2
				it = new Intent("ACAO_TESTE");
				it.addCategory("CATEGORIA_TESTE");
				startActivity(it);
				break;
			case 2:
				//Abrir a tela 3
				it = new Intent("ABRIR_TELA");
				it.addCategory("CATEGORIA_3");
				startActivity(it);
				break;
			case 3:
				//Abrir a tela 4 ou 5
				it = new Intent("ABRIR_TELA");
				it.addCategory("CATEGORIA_DUPLICADA");
				startActivity(it);
				break;
			case 4:
				//Abrir o BroadcastReceiver1
				it = new Intent("TESTE_ABRIR_RECEIVER");
				sendBroadcast(it);
				break;
			default:
				finish();
		}
	}
}