package br.livro.android.cap14.banco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import br.livro.android.cap14.banco.Carro.Carros;

/**
 * Activity que utiliza o TableLayout para editar o carro
 * 
 * @author rlecheta
 * 
 */
public class EditarCarro extends Activity {
	static final int RESULT_SALVAR = 1;
	static final int RESULT_EXCLUIR = 2;

	// Campos texto
	private EditText campoNome;
	private EditText campoPlaca;
	private EditText campoAno;
	private Long id;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_editar_carro);

		campoNome = (EditText) findViewById(R.id.campoNome);
		campoPlaca = (EditText) findViewById(R.id.campoPlaca);
		campoAno = (EditText) findViewById(R.id.campoAno);

		id = null;

		Bundle extras = getIntent().getExtras();
		// Se for para Editar, recuperar os valores ...
		if (extras != null) {
			id = extras.getLong(Carros._ID);

			if (id != null) {
				// é uma edição, busca o carro...
				Carro c = buscarCarro(id);
				campoNome.setText(c.nome);
				campoPlaca.setText(c.placa);
				campoAno.setText(String.valueOf(c.ano));
			}
		}

		ImageButton btCancelar = (ImageButton) findViewById(R.id.btCancelar);
		btCancelar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				setResult(RESULT_CANCELED);
				// Fecha a tela
				finish();
			}
		});

		// Listener para salvar o carro
		ImageButton btSalvar = (ImageButton) findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				salvar();
			}
		});

		ImageButton btExcluir = (ImageButton) findViewById(R.id.btExcluir);

		if (id == null) {
			// Se id está nulo, não pode excluir
			btExcluir.setVisibility(View.INVISIBLE);
		} else {
			// Listener para excluir o carro
			btExcluir.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					excluir();
				}
			});
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Cancela para não ficar nada na tela pendente
		setResult(RESULT_CANCELED);

		// Fecha a tela
		finish();
	}

	public void salvar() {

		int ano = 0;
		try {
			ano = Integer.parseInt(campoAno.getText().toString());
		} catch (NumberFormatException e) {
			// ok neste exemplo, tratar isto em aplicações reais
		}

		Carro carro = new Carro();
		if (id != null) {
			// É uma atualização
			carro.id = id;
		}
		carro.nome = campoNome.getText().toString();
		carro.placa = campoPlaca.getText().toString();
		carro.ano = ano;

		// Salvar
		salvarCarro(carro);

		// OK
		setResult(RESULT_OK, new Intent());

		// Fecha a tela
		finish();
	}

	public void excluir() {
		if (id != null) {
			excluirCarro(id);
		}

		// OK
		setResult(RESULT_OK, new Intent());

		// Fecha a tela
		finish();
	}

	// Buscar o carro pelo id
	protected Carro buscarCarro(long id) {
		return CadastroCarros.repositorio.buscarCarro(id);
	}

	// Salvar o carro
	protected void salvarCarro(Carro carro) {
		CadastroCarros.repositorio.salvar(carro);
	}

	// Excluir o carro
	protected void excluirCarro(long id) {
		CadastroCarros.repositorio.deletar(id);
	}
}
