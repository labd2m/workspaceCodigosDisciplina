package com.studio.mapas.lucas.playervideo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


public class PlayerVideo extends Activity {
    public String titulo;
    public int video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Essa tela será utiizada para exibir qualquer um dos videos do projeto
        //Ela é dinâmica por receber valores via navegação
        exibeVideo();
    }

    public void exibeVideo(){
        Intent it = getIntent();
        if(it != null){
            try {
                //Recupera informações vindas da tela anterior: "DescricaoVideo.java"
                titulo = it.getStringExtra("titulo");
                video = it.getIntExtra("video", 0);

                setContentView(R.layout.activity_player_video);
                setTitle(titulo); // muda o título da activity

                VideoView player = (VideoView) findViewById(R.id.videoView);

                //Configura o endereço do video dentro do pacote do app.
                //Nome do video foi enviado pela tela anterior: "DescricaoVideo.java"
                Uri videoUri = Uri.parse("android.resource://"+ getPackageName() + "/raw/" + video);
                player.setVideoURI(videoUri);

                player.setMediaController(new MediaController(this));
                //Configura para a tela ser fechada quando o video terminar
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        finish();
                    }
                });

                //Inicia o video
                player.requestFocus();
                player.start();
            } catch (Exception e) {
                Log.e("ERRO",e.getMessage());
                Toast.makeText(PlayerVideo.this, "Problemas ao tentar exibir o vídeo. \nTente novamente.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }else{
            finish();
        }
    }
}
