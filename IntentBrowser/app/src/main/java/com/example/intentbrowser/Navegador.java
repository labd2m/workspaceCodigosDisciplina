package com.example.intentbrowser;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class Navegador extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_principal);
    }
    
    public void Navegar(View v){
    	EditText edt = (EditText) findViewById(R.id.endereco);
    	
    	//url deverá ser por exemplo: http://www.google.com
    	String url = edt.getText().toString();
    	
    	//Representa o endereço que desejamos abrir
    	Uri uri = Uri.parse(url);
    	
    	//Cria a Intent com o endereço
    	Intent it = new Intent(Intent.ACTION_VIEW, uri);
    	
    	//Envia a mensagem para o sistema operacional
    	startActivity(it);	  	
    }
    
}


