package com.example.teste1;


import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity{
   
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		//LOG DE VERBOSE
		Log.v("CATEGORIA", "log de verbose");
		//LOG DE DEBUG
		Log.d("CATEGORIA", "log de debug");
		//LOG DE INFO
		Log.i("CATEGORIA", "log de info");
		//LOG DE ALERTA
		Log.w("CATEGORIA", "log de alerta");
		//LOG DE ERRO
		Log.e("CATEGORIA", "log de erro", new RuntimeException("teste de erro"));
		Log.i("connect", "outro tipo de log");
		
    }//fecha onCreate
    
    public void clickButton1(View view) {
    	EditText edt = (EditText) findViewById(R.id.editText1);
    	TextView txt = (TextView) findViewById(R.id.textView2);
    	
    	String valor = edt.getText().toString();
		txt.setText(valor);

		Log.i("Clique", "Clicou no botão!");
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
  
}






