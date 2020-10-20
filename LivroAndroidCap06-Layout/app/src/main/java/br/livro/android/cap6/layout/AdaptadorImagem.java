package br.livro.android.cap6.layout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Adaptador de imagem para os exemplos de GridView, Gallery e ImageSwitcher
 * 
 * @author ricardo
 * 
 */
public class AdaptadorImagem extends BaseAdapter {
	private Context ctx;
	private final int[] imagens;
	private final android.view.ViewGroup.LayoutParams params;
	public AdaptadorImagem(Context c, int[] imagens,
			android.view.ViewGroup.LayoutParams params) {
		this.ctx = c;
		this.imagens = imagens;
		this.params = params;
	}
	public int getCount() {
		return imagens.length;
	}
	public Object getItem(int posicao) {
		return posicao;
	}
	public long getItemId(int posicao) {
		return posicao;
	}
	public View getView(int posicao, View convertView, ViewGroup parent) {
		ImageView img = new ImageView(ctx);
		img.setImageResource(imagens[posicao]);
		img.setAdjustViewBounds(true);
		// i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));
		img.setLayoutParams(params);
		return img;
	}
}