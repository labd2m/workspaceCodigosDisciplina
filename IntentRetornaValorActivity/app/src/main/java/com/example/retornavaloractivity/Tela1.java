package com.example.retornavaloractivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class Tela1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tela1);
    }

    public void Navegar(View v){
       	Intent it = new Intent(this, Tela2.class);      
       	//Envia a mensagem para o SO identificada como 10
       	startActivityForResult(it,10); 
    }

    public void Navegar2(View v){
        Intent it = new Intent(this, Tela2.class);
        //Envia a mensagem para o SO identificada como 20
        startActivityForResult(it,20);
    }
    
    @Override
    protected void onActivityResult(int codigoRequisicao, int codigoResultado, Intent it) {
        //String msg2 = it.getStringExtra("msg");
        if(it == null){
    		//Cancelamento da tela
			Toast.makeText(this, "NENHUM VALOR! " + "\nRequisição: " + codigoRequisicao + "\nResultado: " + codigoResultado, Toast.LENGTH_LONG).show();
    		return;
    	}else if(codigoRequisicao == 10){
            Toast.makeText(this,"Resposta botão 1",Toast.LENGTH_SHORT).show();

    		//recupera o retorno enviado durante o fechamento
    		String msg = it.getStringExtra("msg");
    		
    		if(codigoResultado == 1){
    			//SIM
    			Toast.makeText(this, "Apertou sim: " + msg + "\nRequisição: " + codigoRequisicao + "\nResultado: " + codigoResultado, Toast.LENGTH_LONG).show();
    		}else if(codigoResultado == 2){
    			//NAO
    			Toast.makeText(this, "Apertou não: " + msg + "\nRequisição: " + codigoRequisicao + "\nResultado: " + codigoResultado, Toast.LENGTH_LONG).show();
    		}else if(codigoResultado == 3){
    			Toast.makeText(this, "Destruido: " + msg + "\nRequisição: " + codigoRequisicao + "\nResultado: " + codigoResultado, Toast.LENGTH_LONG).show();
    		}
    	}else if(codigoRequisicao == 20){
            Toast.makeText(this,"Resposta botão 2",Toast.LENGTH_SHORT).show();

            String msg = it.getStringExtra("msg");

            if(codigoResultado == 1){
                //SIM
                Toast.makeText(this, "Apertou sim: " + msg + "\nRequisição: " + codigoRequisicao + "\nResultado: " + codigoResultado, Toast.LENGTH_LONG).show();
            }else if(codigoResultado == 2){
                //NAO
                Toast.makeText(this, "Apertou não: " + msg + "\nRequisição: " + codigoRequisicao + "\nResultado: " + codigoResultado, Toast.LENGTH_LONG).show();
            }else if(codigoResultado == 3){
                Toast.makeText(this, "Destruido: " + msg + "\nRequisição: " + codigoRequisicao + "\nResultado: " + codigoResultado, Toast.LENGTH_LONG).show();
            }

        }
    } 
    
}

