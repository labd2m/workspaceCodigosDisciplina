package br.livro.android.cap14.banco;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Buscar o Carro.
 * 
 * @author rlecheta
 * 
 */
public class BuscarCarro extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_buscar_carro);

		ImageButton btBuscar = (ImageButton) findViewById(R.id.btBuscar);
		btBuscar.setOnClickListener(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Cancela para não ficar nada pendente na tela
		setResult(RESULT_CANCELED);

		// Fecha a tela
		finish();
	}

	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {

		EditText nome = (EditText) findViewById(R.id.campoNome);
		EditText placa = (EditText) findViewById(R.id.campoPlaca);
		EditText ano = (EditText) findViewById(R.id.campoAno);

		// Recupera o nome do carro
		String nomeCarro = nome.getText().toString();

		// Busca o carro pelo nome
		Carro carro = buscarCarro(nomeCarro);

		if (carro != null) {
			// Atualiza os campos com o resultado
			placa.setText(carro.placa);
			ano.setText(String.valueOf(carro.ano));
		} else {
			// Limpa os campos
			placa.setText("");
			ano.setText("");

			Toast.makeText(BuscarCarro.this, "Nenhum carro encontrado", Toast.LENGTH_SHORT).show();
		}
	}

	// Busca um carro pelo nome
	protected Carro buscarCarro(String nomeCarro) {
		Carro carro = CadastroCarros.repositorio.buscarCarroPorNome(nomeCarro);
		return carro;
	}
}
