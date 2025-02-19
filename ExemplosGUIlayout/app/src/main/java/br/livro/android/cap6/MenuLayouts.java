package br.livro.android.cap6;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.livro.android.cap6.layout.ExemploScrollView;

public class MenuLayouts extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] items = new String[] { 
				"LinearLayout horizontal",
				"LinearLayout vertical",
				"LinearLayout gravity",
				"LinearLayout weight",
				"LinearLayout weight colors",
				"RelativeLayout Form",
				"LinearLayout aninhado",
				"ScrollView",
                "Muda Orientação"};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				//LinearLayout (default)
				startActivity(R.layout.linear_layout_horizontal);
				break;
			case 1:
				//LinearLayout Vertical
				startActivity(R.layout.linear_layout_vertical);
				break;
			case 2:
				//LinearLayout aninhado para montar um Formulário
				startActivity(R.layout.linear_layout_gravity);
				break;
			case 3:
				startActivity(R.layout.linear_layout_weight_text_2);
				break;
			case 4:
				startActivity(R.layout.linear_layout_weight_colors);
				break;
			case 5:
				//Formulário
				startActivity(R.layout.relative_layout_form);
				break;
			case 6:
				//Aninhado
				startActivity(R.layout.linear_layout_form_aninhado);
				break;
			case 7:
				startActivity(new Intent(this,ExemploScrollView.class));
				break;
            case 8:
                startActivity(new Intent(this,MudaOrientacao.class));
                break;
		}
	}

	private void startActivity(int layoutId) {
		Intent intent = new Intent(this,ActivityLayoutIdGenerica.class);
		intent.putExtra("layoutResId", layoutId);
		startActivity(intent);
	}

}