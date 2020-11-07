package android.teste.lucasvegi.ciclodevidaactivities;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Tela2 extends Ciclo {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        
        //Log.i("Ciclo De Vida", getClassName() + ".onCreate() chamado.");
    }
    
    public void Tela1(View v){
    	Intent it = new Intent(this, Ciclo.class);
    	
    	//USADO PARA REORDENADAR A PILHA, LEVANDO PARA O TOPO A ACTIVITY DESEJADA
    	//it.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    	
    	startActivity(it);
        //finish();
    }
    
    public void Tela3(View v){
        Intent it = new Intent(this, Tela3.class);
        startActivity(it);
    }


    
}
