package com.example.retornavaloractivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.widget.Toast;

public class Tela2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tela2);
        
      	Intent it = new Intent();
       	//Seta msg de retorno
       	it.putExtra("msg", "Destruí!!!");

       	//Comentar para demestrar Intent nula no retorno quando fechar tela no back
    	setResult(3, it);
    }

    public void SIM(View v){
       	Intent it = new Intent();    	
       	
       	//Seta msg de retorno
       	it.putExtra("msg", "CLIQUEI EM SIM!!!");     	
       	
       	//Seta o codigoResultado e a intent de retorno
       	setResult(1, it);
       
       	finish();
    }
    
    public void NAO(View v){
       	Intent it = new Intent();    	
       	
       	//Seta msg de retorno
       	it.putExtra("msg", "CLIQUEI EM NÃO!!!");     	
       	
       	//Seta o codigoResultado e a intent de retorno
       	setResult(2, it);
       
       	finish();
    }
    
    /*@Override
    protected void onDestroy() {
       	Intent it = new Intent();    	
       	
       	//Seta msg de retorno
       	it.putExtra("msg", "Destrui!!!");     
    	
    	setResult(3, it);
		super.onDestroy();
    }*/
    
}
