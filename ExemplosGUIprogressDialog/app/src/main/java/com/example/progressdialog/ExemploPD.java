package com.example.progressdialog;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class ExemploPD extends Activity {
	private ProgressDialog PD;
	private long i;
    private long t = 1000;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_exemplo_pd);
    }

    public void Processar(View v){
    	PD = ProgressDialog.show(this, "Executando Operação", "Aguarde um momento...");

    	//permite cancelar a exibição do PD ao tocar fora dele
    	PD.setCancelable(true);
		PD.setCanceledOnTouchOutside(true);
   
    	i = 0;
    	
    	new Thread() { 		 
    		public void run() {    		   		     			
	    			//Inicia o processamento
    				while(i < 60){
	    				Log.i("Teste", "Valor: " + i);
	    				i++;

                        try {
                            sleep(500);
                        }catch (Exception e){

                        }
	    			}
	    			//fecha o ProgressDialog
	    			PD.dismiss();
    		}
    	}.start();
    	
    }   
}
