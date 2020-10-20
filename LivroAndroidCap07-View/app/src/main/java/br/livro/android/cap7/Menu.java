package br.livro.android.cap7;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.livro.android.cap7.lista.ExemploExpandableList;
import br.livro.android.cap7.view.ActivityLayoutIdGenerica;
import br.livro.android.cap7.view.ExemploAlertaConfirmacao;
import br.livro.android.cap7.view.ExemploAutoCompleteTextView;
import br.livro.android.cap7.view.ExemploBarraProgresso;
import br.livro.android.cap7.view.ExemploCheckRadio;
import br.livro.android.cap7.view.ExemploImageButton;
import br.livro.android.cap7.view.ExemploImagem;
import br.livro.android.cap7.view.ExemploListView;
import br.livro.android.cap7.view.ExemploMenu;
import br.livro.android.cap7.view.ExemploProgressDialog;
import br.livro.android.cap7.view.ExemploSpinner;
import br.livro.android.cap7.view.ExemploSubMenu;
import br.livro.android.cap7.view.ExemploToast;
import br.livro.android.cap7.view.ExemploToggleButton;

/**
 * Exemplo das Views do Android
 * 
 * @author ricardo
 *
 */
public class Menu extends ListActivity {
	private static final String[] ops = new String[] { 
			"TextView",
			"EditText",
			"AutoCompleteTextView",
			"Imagem",
			"ImageButton",
			"Imagem Web - ProgressDialog",
			"Toast",
			"Alerta Confirmação",
			"Barra de Progresso",
			"Check e Radio",
			"Expandable List",
			"Spinner",
			"ToogleButton",
			"ListView",
			"Menu",
			"SubMenu"};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ops));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
			case 0:
				startActivity(R.layout.exemplo_text_view);
				break;
			case 1:
				startActivity(R.layout.exemplo_edit_text);
				break;
			case 2:
				startActivity(new Intent(this,ExemploAutoCompleteTextView.class));
				break;
			case 3:
				startActivity(new Intent(this,ExemploImagem.class));
				break;
			case 4:
				startActivity(new Intent(this,ExemploImageButton.class));
				break;
			case 5:
				startActivity(new Intent(this,ExemploProgressDialog.class));
				break;
			case 6:
				startActivity(new Intent(this,ExemploToast.class));
				break;
			case 7:
				startActivity(new Intent(this,ExemploAlertaConfirmacao.class));
				break;
			case 8:
				startActivity(new Intent(this,ExemploBarraProgresso.class));
				break;
			case 9:
				startActivity(new Intent(this,ExemploCheckRadio.class));
				break;
			case 10:
				startActivity(new Intent(this,ExemploExpandableList.class));
				break;
			case 11:
				startActivity(new Intent(this,ExemploSpinner.class));
				break;
			case 12:
				startActivity(new Intent(this,ExemploToggleButton.class));
				break;
			case 13:
				startActivity(new Intent(this,ExemploListView.class));
				break;
			case 14:
				startActivity(new Intent(this,ExemploMenu.class));
				break;
			case 15:
				startActivity(new Intent(this,ExemploSubMenu.class));
				break;
			default:
				finish();
				break;
		}
	}

	/**
	 * Exibe apenas um layout, baseado no id do XML fornecido.
	 * 
	 * Utiliza a Activity ActivityLayoutIdGenerica que recebe o id do layout para abrir
	 * 
	 * @param layoutId
	 */
	private void startActivity(int layoutId) {
		Intent intent = new Intent(this,ActivityLayoutIdGenerica.class);
		intent.putExtra("layoutResId", layoutId);
		startActivity(intent);
	}

}