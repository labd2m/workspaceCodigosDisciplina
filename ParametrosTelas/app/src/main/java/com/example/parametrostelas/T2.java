package com.example.parametrostelas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class T2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_t2);
		
		Intent it = getIntent();
		Bundle sacola = it.getExtras();
		
		TextView txtNome = (TextView) findViewById(R.id.txtNome);
		TextView txtCPF = (TextView) findViewById(R.id.txtCPF);
		
		txtNome.setText(sacola.getString("nome"));
		txtCPF.setText(sacola.getString("cpf"));
		
	}
	
}
