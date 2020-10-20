package br.livro.android.cap14.banco;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import br.livro.android.cap14.banco.Carro.Carros;

/**
 * Activity que demonstra o cadastro de carros:
 * 
 * - ListActivity: para listar os carros - RepositorioCarro para salvar os dados
 * no banco - Carro
 * 
 * @author rlecheta
 * 
 */
public class CadastroCarros extends ListActivity {
	protected static final int INSERIR_EDITAR = 1;
	protected static final int BUSCAR = 2;

	public static RepositorioCarro repositorio;

	private List<Carro> carros;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		repositorio = new RepositorioCarroScript(this);
		atualizarLista();
	}

	protected void atualizarLista() {
		// Pega a lista de carros e exibe na tela
		carros = repositorio.listarCarros();

		// Adaptador de lista customizado para cada linha de um carro
		setListAdapter(new CarroListAdapter(this, carros));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, INSERIR_EDITAR, 0, "Inserir Novo").setIcon(R.drawable.novo);
		menu.add(0, BUSCAR, 0, "Buscar").setIcon(R.drawable.pesquisar);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// Clicou no menu
		switch (item.getItemId()) {
		case INSERIR_EDITAR:
			// Abre a tela com o formulário para adicionar
			startActivityForResult(new Intent(this, EditarCarro.class), INSERIR_EDITAR);
			break;
		case BUSCAR:
			// Abre a tela para buscar o carro pelo nome
			startActivity(new Intent(this, BuscarCarro.class));
			break;
		}
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int posicao, long id) {
		super.onListItemClick(l, v, posicao, id);
		editarCarro(posicao);
	}

	// Recupera o id do carro, e abre a tela de edição
	protected void editarCarro(int posicao) {
		// Usuário clicou em algum carro da lista
		// Recupera o carro selecionado
		Carro carro = carros.get(posicao);
		// Cria a intent para abrir a tela de editar
		Intent it = new Intent(this, EditarCarro.class);
		// Passa o id do carro como parâmetro
		it.putExtra(Carros._ID, carro.id);
		// Abre a tela de edição
		startActivityForResult(it, INSERIR_EDITAR);
	}

	@Override
	protected void onActivityResult(int codigo, int codigoRetorno, Intent it) {
		super.onActivityResult(codigo, codigoRetorno, it);

		// Quando a activity EditarCarro retornar, seja se foi para adicionar vamos atualizar a lista
		if (codigoRetorno == RESULT_OK) {
			// atualiza a lista na tela
			atualizarLista();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Fecha o banco
		repositorio.fechar();
	}
}