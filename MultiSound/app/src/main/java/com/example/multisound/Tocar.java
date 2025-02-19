package com.example.multisound;

import java.util.Locale;

import junit.framework.Test;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.NavUtils;

//PARA UTILIZAR OS MÉTODOS DA BIBLIOTECA DE SINTETIZAÇÃO DE VOZ IMPLEMENTADAS ABAIXO
//A ACTIVITY EM QUESTÃO DEVE SEMPRE IMPLEMENTAR OnInitListener
public class Tocar extends Activity implements OnInitListener { //<<<<-------------------------------

	//DECLARE SEMPRE ESSES DOIS ATRIBUTOS NESSE PONTO POIS ELES SERÃO UTILIZADOS POR MÉTODOS ABAIXO
	//RESPONSÁVEIS POR CONFIGURAR A SINTETIZAÇÃO DE VOZ
	private TextToSpeech myTTS;
    private int MY_DATA_CHECK_CODE = 0;
	//---------------------------------------------
    
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tocar);
        
    	//DECLARE SEMPRE NESSE PONTO ESSAS 3 LINHAS DE CÓDIGO POIS ELAS SERÃO UTILIZADOS POR MÉTODOS ABAIXO
    	//RESPONSÁVEIS POR CONFIGURAR A SINTETIZAÇÃO DE VOZ
        
        //check for TTS data
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
        
        //------------------------------------------------------------------
        
    }

    //Método de clique do botão aplausos. Ele toca um arquivo de audio adicionado à pasta /res/raw/
	public void tocar(View v){
    	MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.aplausos);
    	mp.start();
    }
    
    //Método de clique do botão falar. Fala em inglês o texto que foi informado no editText
	public void falar(View v){
    	EditText texto = (EditText) findViewById(R.id.editFala);
    	
    	//speak straight away
        myTTS.speak(texto.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
        //O PRIMEIRO PARAMETRO DO MÉTODO SPEAK É O TEXTO QUE SERÁ FALADO PELO SINTETIZADOR
        //OS OUTROS DOIS PARÂMETROS PODEM SER MATIDOS PADRÃO PARA TODOS OS CASOS
    }
    

	//ESSES DOIS MÉTODOS DECLARADOS ABAIXO DEVEM SER SEMPRE MANTIDOS NAS ACTIVITIES QUE IRÃO SINTETIZAR VOZ
	//ELES SÃO RESPONSÁVEIS POR FAZER A CONFIGURAÇÃO DA VOZ, IDIOMA A SER FALADO, ETC.
	
        //act on result of TTS data check
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
            myTTS = new TextToSpeech(this, this);
            }
            else {
                    //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }
 
        //setup TTS
    public void onInit(int initStatus) {
 
            //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
        	//myTTS.setLanguage(Locale.FRENCH);
        	
        	if(myTTS.isLanguageAvailable(new Locale("spa"))==TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(new Locale("spa"));
            else
            	Toast.makeText(this, "Deu merda", Toast.LENGTH_SHORT).show();
        }
        else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
        
        //ESTA LINHA PERMITE QUE O PROGRAMA FALE COM O USUÁRIO NO MOMENTO QUE O SOFTWARE É INICIALIZADO
        //O PRIMEIRO PARÂMETRO PASSADO COMO NA CHAMADA DO MÉTODO SPEAK É A STRING QUE SERÁ FALADA
        
        //myTTS.speak("Hello, I'm speaking with you!", TextToSpeech.QUEUE_FLUSH, null);
        
    }
    
}
