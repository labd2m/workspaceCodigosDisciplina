package com.example.eventobotao;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tela_principal extends Activity {

	TextView txt;
	EditText edt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_principal);
		
		edt = (EditText) findViewById(R.id.edtPrincipal);
		txt = (TextView) findViewById(R.id.txtPrincipal);
		
	}
	
	public void clickBotao(View v){
		String texto = edt.getText().toString();
		txt.setText(texto);
		Log.i("CATEGORIA","EXECUTAMOS O LOG");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_principal, menu);
		return true;
	}

}
