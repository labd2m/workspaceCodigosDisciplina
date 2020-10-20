package br.livro.android.cap7.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import br.livro.android.cap7.R;

/**
 * Exemplo de como fazer o download de uma imagem da Web através de uma URL
 * 
 * A InputStream da imagem é convertida em um Bitmap para ser utilizado no
 * ImageView
 * 
 * @author ricardo
 * 
 */
public class ExemploProgressDialog extends Activity {
	private static final String URL = "http://chart.apis.google.com/chart?chs=150x150&cht=qr&chl=www.livroandroid.com.br&choe=UTF-8";
	private ProgressDialog dialog;
	private Handler handler = new Handler();

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.exemplo_progress_dialog);

		//Abre a janela com a barra de progresso 
		dialog = ProgressDialog.show(this,"Exemplo", "Buscando imagem, por aguarde...", false,true);

//		ProgressDialog dialog = new ProgressDialog(this);
//		dialog.setTitle("Exemplo");
//		dialog.setMessage("Buscando imagem, por aguarde...");
//		dialog.setIndeterminate(true);
//		dialog.setCancelable(true);
//		dialog.show();

		downloadImagem(URL);
	}
	//Faz o download da imagem em uma nova Thread
	private void downloadImagem(final String urlImg) {
		new Thread() {
			@Override
			public void run() {
				try {
					//Faz o download da imagem
					URL url = new URL(urlImg);
					InputStream is = url.openStream();
					final Bitmap imagem = BitmapFactory.decodeStream(is);
					is.close();

					//Atualiza a tela
					atualizaTela(imagem);

				} catch (MalformedURLException e) {
					return;
				} catch (IOException e) {
					//Uma aplicação real deveria tratar este erro
					Log.e("Erro ao fazer o download", e.getMessage(),e);
				}
			}
		}.start();
	}
	//Utiliza um Handler para atualizar a tela
	private void atualizaTela(final Bitmap imagem) {
		handler.post(new Runnable(){
			public void run() {
				//Fecha a janela de progresso
				dialog.dismiss();
				ImageView imgView = (ImageView) findViewById(R.id.img);
				imgView.setImageBitmap(imagem);
			}});
	}
}
