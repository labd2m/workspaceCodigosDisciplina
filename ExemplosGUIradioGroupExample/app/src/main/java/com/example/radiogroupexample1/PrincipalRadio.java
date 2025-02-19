package com.example.radiogroupexample1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class PrincipalRadio extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_radio);
    }
    
    public void Resposta(View v){
    	RadioGroup rg = (RadioGroup) findViewById(R.id.Grupo);
    	
    	if(rg.getCheckedRadioButtonId() == R.id.Sim)
    		Toast.makeText(this, "Escolhe SIM", Toast.LENGTH_SHORT).show();
    	else
    		Toast.makeText(this, "Escolhe NÃO", Toast.LENGTH_SHORT).show();
    }
}
