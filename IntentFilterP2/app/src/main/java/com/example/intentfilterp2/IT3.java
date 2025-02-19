package com.example.intentfilterp2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class IT3 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_it3); 
        
        Intent it = getIntent();
        
        if(it != null){
        	String msg = it.getStringExtra("msg");
        	
        	TextView txt = (TextView) findViewById(R.id.txt);
        	txt.setText("FOI FEITA A INTEGRAÇÃO COM CATEGORIA: " + msg);
        }else{
        	TextView txt = (TextView) findViewById(R.id.txt);
        	txt.setText("NÃO FOI FEITA A INTEGRAÇÃO!");
        }
        
    } 

    
}

