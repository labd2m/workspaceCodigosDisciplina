package br.livro.android.cap7.view;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import br.livro.android.cap7.R;
import br.livro.android.cap7.view.adapter.Smile;
import br.livro.android.cap7.view.adapter.SmileAdapter;

/**
 * Exemplo de ListView que utiliza o adaptador customizado SmileAdapter
 * 
 * @author ricardo
 * 
 */
public class ExemploListView extends Activity implements OnItemClickListener {
	protected static final String CATEGORIA = "livro";
	private ListView listView;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.layout_listview);

		ArrayList<Smile> lista = new ArrayList<Smile>();
		lista.add(new Smile("Feliz", Smile.FELIZ));
		lista.add(new Smile("Triste", Smile.TRISTE));
		lista.add(new Smile("Louco", Smile.LOUCO));

		// Adaptador customizado
		SmileAdapter adapter = new SmileAdapter(this, lista);

		// ListView
		listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
		Log.i(CATEGORIA, "ExemploListView.onItemClick posicao: " + posicao + ", id: " + id);

		// Recupera o Smile naquela posição
		Smile smile = (Smile) listView.getAdapter().getItem(posicao);

		// Exibe um alerta
		Toast.makeText(this, "Smile selecionado: " + smile.nome, Toast.LENGTH_SHORT).show();
	}
}
