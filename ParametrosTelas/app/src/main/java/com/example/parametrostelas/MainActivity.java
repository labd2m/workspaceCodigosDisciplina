package com.example.parametrostelas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void navegar(View v){
		EditText edtNome = (EditText) findViewById(R.id.edtNome);
		EditText edtCPF = (EditText) findViewById(R.id.edtCPF);
		
		Intent it = new Intent(this,T2.class);
		
		Bundle sacola = new Bundle();
		
		sacola.putString("nome", edtNome.getText().toString());
		sacola.putString("cpf", edtCPF.getText().toString());
		
		it.putExtras(sacola);
		startActivity(it);
	}

}
