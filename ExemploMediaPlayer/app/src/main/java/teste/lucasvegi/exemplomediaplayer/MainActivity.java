package teste.lucasvegi.exemplomediaplayer;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    public MediaPlayer mp;
    public Button btnPlay;
    public Button btnPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recupera referências views
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPause = (Button) findViewById(R.id.btnPause);

        //Configura a música
        mp = MediaPlayer.create(this,R.raw.som);
       // mp.setLooping(true);    //reinicia a música ao final

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //Executa quando a musica termina a sua execução
                Toast.makeText(getBaseContext(),"Terminou a música",Toast.LENGTH_SHORT).show();
                btnPause.setEnabled(false); //desativa botão pausar
                btnPlay.setEnabled(true); //ativa botão play
            }
        });

    }

    public void playMusic(View v){
        mp.start();     //INICIA O SOM
        btnPause.setEnabled(true); //ativa botão pausar
        btnPlay.setEnabled(false); //desativa botão play
    }

    public void pauseMusic(View v){
        mp.pause();     //PAUSA O SOM
        btnPause.setEnabled(false); //desativa botão pausar
        btnPlay.setEnabled(true); //ativa botão play
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp != null){
            mp.stop();  //PARA O SOM
            mp.release();   //LIBERA RECURSOS DO SISTEMA
        }
    }
}
