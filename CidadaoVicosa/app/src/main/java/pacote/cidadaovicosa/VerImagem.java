package pacote.cidadaovicosa;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class VerImagem extends Activity {
	private String URLimagem;
	private String URLvideo;
	private ProgressDialog dialog;
	private Handler handler = new Handler();
	private String titulo;
	
	//novidade
	private String notaMedia;
	private String dtOcorrencia;
	private String hrOcorrencia;
	private String categoria;
	private String subcategoria;
	private String dtCriacao;
	private String hrCriacao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_ver_imagem);
		
		Intent it = getIntent();
		
		if(it != null){
			try {
				URLimagem = it.getStringExtra("foto");
				URLvideo = it.getStringExtra("video");
				
				titulo = it.getStringExtra("titulo");
				setTitle(titulo); // muda o título da activity
				
				//novidade
				dtOcorrencia = it.getStringExtra("dtOcorrencia");
				hrOcorrencia = it.getStringExtra("hrOcorrencia");
				dtCriacao = it.getStringExtra("dtCriacao");
				hrCriacao = it.getStringExtra("hrCriacao");
				categoria = it.getStringExtra("categoria");
				subcategoria = it.getStringExtra("subcategoria");
				notaMedia = it.getStringExtra("notaMedia");
				
				if(!URLimagem.equals("")){
					//Abre a janela com a barra de progresso 
						
					dialog = new ProgressDialog(this);
					//dialog.setTitle("Exemplo");
					dialog.setMessage("Carregando...");
					dialog.setIndeterminate(false);
					dialog.setCancelable(true);
					dialog.show();

					downloadImagem(URLimagem);
				}
			} catch (Exception e) {
				Toast.makeText(VerImagem.this, "Problemas ao tentar exibir o imagem. \nTente novamente.", Toast.LENGTH_SHORT).show();
				finish();
			}
	
		}else{
			finish();
		}
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
					ImageView imgView = (ImageView) findViewById(R.id.imagemColaboracao);
					imgView.setImageBitmap(imagem);
				}});
		}
	 
	 //Utilizado quando a foto é clicada
	 public void informacaoFoto(View v){
		 //novidade
		 if(!dtOcorrencia.equals("") && !hrOcorrencia.equals("")){
			 Toast.makeText(VerImagem.this, " Título: "+ titulo +"\n Data ocorrência: " + dtOcorrencia +
					 						"\n Hora ocorrência: " + hrOcorrencia + "\n Data criação: " + dtCriacao +
					 						"\n Hora criação: " + hrCriacao +"\n Categoria: " + categoria + 
					 						"\n Subcategoria: " + subcategoria + "\n Nota: " + notaMedia, 
					 						Toast.LENGTH_LONG).show();
		 }else{
			 Toast.makeText(VerImagem.this, " Título: "+ titulo + "\n Data criação: " + dtCriacao +
						"\n Hora criação: " + hrCriacao +"\n Categoria: " + categoria + 
						"\n Subcategoria: " + subcategoria + "\n Nota: " + notaMedia, 
						Toast.LENGTH_LONG).show();
		 }
			
		 //Toast.makeText(VerImagem.this, titulo, Toast.LENGTH_LONG).show();
	 }

}
