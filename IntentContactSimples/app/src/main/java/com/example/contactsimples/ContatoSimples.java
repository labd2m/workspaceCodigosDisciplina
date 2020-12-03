package com.example.contactsimples;

import com.example.navegador.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class ContatoSimples extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tela1);
    }

	/*public void Navegar(View v){

		Uri uri = Uri.parse("content://com.android.contacts/contacts/1");

		// testar com contato não cadastrado - Toast
		//Uri uri = Uri.parse("content://com.android.contacts/contacts/3");

        Intent it = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(it);
	}*/

    /*public void Navegar(View v){
    	
    	Uri uri = Uri.parse("content://com.android.contacts/contacts/");
    	Intent it = new Intent(Intent.ACTION_PICK, uri);
    	startActivity(it);
    }*/

    public void Navegar(View v){
    	
    	Uri uri = Uri.parse("content://com.android.contacts/contacts/");
    	Intent it = new Intent(Intent.ACTION_PICK, uri);
    	startActivityForResult(it, 10);
    }


    @Override
    protected void onActivityResult(int codigoRequisicao, int codigoResultado, Intent it) {

		Log.d("integracao", "Passei por aqui!");

    	if(codigoRequisicao == 10){
    		Uri uri = it.getData();

			Toast.makeText(this, "Cod. Requisição:\t" + codigoRequisicao +
							"\nCod. Resultado:\t" + codigoResultado +
							"\n" + uri, Toast.LENGTH_LONG).show();
    	}
    	
    	if(codigoRequisicao == 5){
			Toast.makeText(this, "5", Toast.LENGTH_LONG).show();
    	}
    	
    	if(codigoRequisicao == 3){
			Toast.makeText(this, "3", Toast.LENGTH_LONG).show();
    	}
    }
       
}
