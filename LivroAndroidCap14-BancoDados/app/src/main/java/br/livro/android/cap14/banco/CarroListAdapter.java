package br.livro.android.cap14.banco;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Adapter customizado que exibe o layout definido em smile_row.xml
 * 
 * As imagens são exibidas no widget ImageView
 * 
 * @author ricardo
 * 
 */
public class CarroListAdapter extends BaseAdapter {
	private Context context;
	private List<Carro> lista;

	public CarroListAdapter(Context context, List<Carro> lista) {
		this.context = context;
		this.lista = lista;
	}

	public int getCount() {
		return lista.size();
	}

	public Object getItem(int position) {
		return lista.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// Recupera o Carro da posição atual
		Carro c = lista.get(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.carro_linha_tabela, null);

		// Atualiza o valor do TextView
		TextView nome = (TextView) view.findViewById(R.id.nome);
		nome.setText(c.nome);

		TextView placa = (TextView) view.findViewById(R.id.placa);
		placa.setText(c.placa);

		TextView ano = (TextView) view.findViewById(R.id.ano);
		ano.setText(String.valueOf(c.ano));

		return view;
	}
}