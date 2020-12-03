package br.livro.android.cap6;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;

public class MudaOrientacao extends Activity {

	public EditText campo;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout_vertical);
        
        campo = (EditText) findViewById(R.id.etdTexto);
        
        String valor = null;

        //Comentar os dois IFs a seguir para ver os dados se perderem
        if(savedInstanceState != null) {
            valor = savedInstanceState.getString("nome_campo");
        }

        if(valor != null)
            campo.setText(valor);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState)
    {
        outState.putString("nome_campo", campo.getText().toString());
        Log.d("SalvarEstado","Estado foi salvo!");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("SalvarEstado","DESTRUI A ACTIVITY!");
    }
    
}

