package br.livro.android.cap7.view.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.livro.android.cap7.R;

/**
 * Adaptador customizado que exibe o layout definido em smile_row.xml
 * 
 * As imagens são exibidas utilizando um ImageView
 * 
 * @author ricardo
 * 
 */
public class SmileAdapter extends BaseAdapter {
	protected static final String CATEGORIA = "livro";
	private Context context;
	private List<Smile> lista;
	public SmileAdapter(Context context, List<Smile> lista) {
		this.context = context;
		this.lista = lista;
	}
	public int getCount() {
		return lista.size();
	}
	public Object getItem(int posicao) {
		Smile smile = lista.get(posicao);
		Log.i(CATEGORIA,"SmileAdapter.getItem("+posicao+") > " + smile);
		return smile;
	}
	public long getItemId(int posicao) {
		Log.i(CATEGORIA,"SmileAdapter.getItemId("+posicao+") > " + posicao);
		return posicao;
	}
	public View getView(int posicao, View convertView, ViewGroup parent) {
		Log.i(CATEGORIA,"SmileAdapter.getView("+posicao+")");

		// Recupera o Smile da posição atual
		Smile smile = lista.get(posicao);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.smile_detalhes, null);

		// Atualiza o valor do Text para o nome do Smile
		TextView textNome = (TextView) v.findViewById(R.id.nome);
		textNome.setText(smile.nome);

		// Atualiza a imagem para a imagem do Smile
		// A imagem é definda por um recurso no @drawable
		ImageView img = (ImageView) v.findViewById(R.id.img);
		img.setImageResource(smile.getImagem());
		return v;
	}
}
