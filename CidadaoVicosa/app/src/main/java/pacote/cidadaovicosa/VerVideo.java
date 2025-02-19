package pacote.cidadaovicosa;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VerVideo extends Activity {

	private String URLvideo;
	private String titulo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent it = getIntent();
		
		if(it != null){
			try {
				URLvideo = it.getStringExtra("video");
				titulo = it.getStringExtra("titulo");
				
				setContentView(R.layout.layout_ver_video);
				setTitle(titulo); // muda o título da activity
				
				VideoView player = (VideoView) findViewById(R.id.playerVideo);
				
				Uri videoUri = Uri.parse(URLvideo);
				player.setVideoURI(videoUri);
				player.setMediaController(new MediaController(this));
				player.requestFocus();
				player.start();		
			} catch (Exception e) {
				Toast.makeText(VerVideo.this, "Problemas ao tentar exibir o vídeo. \nTente novamente.", Toast.LENGTH_SHORT).show();
				finish();
			}
			
		}else{
			finish();
		}
	}
	
	 //Utilizado quando a video é clicado
	 public void informacaoVideo(View v){
		 Toast.makeText(VerVideo.this, titulo, Toast.LENGTH_LONG).show();
	 }

}
