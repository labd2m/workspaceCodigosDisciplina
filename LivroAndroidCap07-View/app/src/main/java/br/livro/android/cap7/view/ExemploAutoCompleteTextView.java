package br.livro.android.cap7.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import br.livro.android.cap7.R;

/**
 * Exemplo de AutoComplete para estados do Brasil
 * 
 * @author ricardo
 * 
 */
public class ExemploAutoCompleteTextView extends Activity {
	private static final String[] ESTADOS = new String[] { "Acre", "Alagoas", "Amapá","Amazonas", "Bahia", 
		"Ceará", "Distrito Federal", "Goiás","Espírito Santo", "Maranhão", "Mato Grosso", "Mato Grosso do Sul",
		"Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí",	"Rio de Janeiro", "Rio Grandedo Norte", 
		"Rio Grande do Sul","Rondônia", "Roraima", "São Paulo", "Santa Catarina", "Sergipe","Tocantins"
	};
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.auto_complete_textview);
		
		//Cria um ArrayAdapter para exibir os estados
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, ESTADOS);

		AutoCompleteTextView estatos = (AutoCompleteTextView) findViewById(R.id.estados);
		estatos.setAdapter(adaptador);
	}
}