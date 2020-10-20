package com.example.intentphone;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class Phone extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_principal);
    }
    
    public void Ligar(View v){
    	
    	//Representa o telefone para onde ligar
    	Uri uri = Uri.parse("tel:5556");
    	
    	//Cria a Intent com o telefone
    	Intent it = new Intent(Intent.ACTION_CALL, uri);
    	
    	//Envia a mensagem para o sistema operacional
    	startActivity(it);	  	
    }
    
}


