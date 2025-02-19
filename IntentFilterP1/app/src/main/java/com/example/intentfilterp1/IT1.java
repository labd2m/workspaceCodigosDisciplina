package com.example.intentfilterp1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;

public class IT1 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_it1);        
    }
    
    public void viajar(View V){
    	Intent it = new Intent("ACAO_DESEJADA");
    	it.putExtra("msg", "Eu fiz a integração!");
    	startActivity(it);
    }
    
    public void viajar2(View V){
    	Intent it = new Intent("ACAO_DESEJADA");
    	it.addCategory("CATEGORIA_CUSTOMIZADA");
    	
    	it.putExtra("msg", "Eu fiz a integração!");
    	startActivity(it);
    }
    
}

