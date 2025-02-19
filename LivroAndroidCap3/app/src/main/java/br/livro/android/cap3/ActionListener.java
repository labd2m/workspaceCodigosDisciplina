package br.livro.android.cap3;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ActionListener extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exemplo_listener);
        
        //busca TextViewpelo id
        final TextView txtV = (TextView) findViewById(R.id.campoResultado);
        final EditText edit = (EditText) findViewById(R.id.campoNome);
        Button but = (Button) findViewById(R.id.butaoOk);
        
        
		//Informa o Listener de um Botão no click
        but.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v){
        		String nome = edit.getText().toString();
        		txtV.setText("Obrigado " + nome);
        		Log.i("Botão", "Botão foi clicado!"); //Log de Informação de Clicks
        	}
        });
             
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exemplo_listener, menu);
        return true;
    }

    
}
