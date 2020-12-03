package br.livro.android.cap7.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import br.livro.android.cap7.R;

public class ExemploSpinner extends Activity implements AdapterView.OnItemSelectedListener {
	// Planetas
	private int[] imagens = { R.drawable.mercurio, R.drawable.venus, R.drawable.terra, R.drawable.marte,
			R.drawable.jupiter, R.drawable.saturno, R.drawable.urano, R.drawable.netuno, R.drawable.plutao };
	
	private String[] planetas = new String[] { "Mercúrio", "Venus", "Terra", "Marte", "Júptier", "Saturno", "Urano",
			"Netuno", "Plutão" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exemplo_spinner);

		Spinner combo = (Spinner) findViewById(R.id.comboPlanetas);
		combo.setOnItemSelectedListener(this); //configura metodo de seleção

		//configura adaptador
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, planetas);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
		
		//informa qual é o adaptador
		combo.setAdapter(adaptador);
	}

	public void onItemSelected(AdapterView parent, View v, int posicao, long id) {
		// Atualiza a imagem
		ImageView imagem = (ImageView) findViewById(R.id.img);
		imagem.setImageResource(imagens[posicao]);

		//Exibe o nome do planeta escolhido
		String n = parent.getItemAtPosition(posicao).toString();
        Toast.makeText(this,n,Toast.LENGTH_LONG).show();
	}

	public void onNothingSelected(AdapterView arg0) {
		//método vazio
        Toast.makeText(this,"Nada selecionado",Toast.LENGTH_SHORT).show();
	}
}
