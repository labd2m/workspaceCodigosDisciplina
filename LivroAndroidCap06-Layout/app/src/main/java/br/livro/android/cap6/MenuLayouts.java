package br.livro.android.cap6;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.livro.android.cap6.layout.ExemploScrollView;
import br.livro.android.cap6.layout.api.ExemploLinearLayoutAPI;
import br.livro.android.cap6.layout.api.ExemploTableLayoutAPI;

public class MenuLayouts extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] items = new String[] { 
				"FrameLayout background",
				"LinearLayout horizontal",
				"LinearLayout vertical",
				"LinearLayout gravity",
				"LinearLayout weight",
				"LinearLayout weight colors",
				"LinearLayout Form (weight)",
				"TableLayout shrink",
				"TableLayout stretch",
				"TableLayout Form",
				"RelativeLayout Form",
				"AbsoluteLayout Form",
				"LinearLayout aninhado",
				"ScrollView",
				"LinearLayout API",
				"TableLayout API"};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(R.layout.frame_layout_background);
				break;
			case 1:
				//LinearLayout (default)
				startActivity(R.layout.linear_layout_horizontal);
				break;
			case 2:
				//LinearLayout Vertical
				startActivity(R.layout.linear_layout_vertical);
				break;
			case 3:
				//LinearLayout aninhado para montar um Formulário
				startActivity(R.layout.linear_layout_gravity);
				break;
			case 4:
				startActivity(R.layout.linear_layout_weight_text_2);
				break;
			case 5:
				startActivity(R.layout.linear_layout_weight_colors);
				break;
			case 6:
				startActivity(R.layout.linear_layout_form);
				break;
			case 7:
				startActivity(R.layout.table_layout_shrink);
				break;
			case 8:
				startActivity(R.layout.table_layout_stretch);
				break;
			case 9:
				startActivity(R.layout.table_layout_form);
				break;
			case 10:
				//Formulário
				startActivity(R.layout.relative_layout_form);
				break;
			case 11:
				//Formulário
				startActivity(R.layout.absolute_layout_form);
				break;
			case 12:
				//Aninhado
				startActivity(R.layout.linear_layout_form_aninhado);
				break;
			case 13:
				startActivity(new Intent(this,ExemploScrollView.class));
				break;
			case 14:
				startActivity(new Intent(this,ExemploLinearLayoutAPI.class));
				break;
			case 15:
				startActivity(new Intent(this,ExemploTableLayoutAPI.class));
				break;
		}
	}

	private void startActivity(int layoutId) {
		Intent intent = new Intent(this,ActivityLayoutIdGenerica.class);
		intent.putExtra("layoutResId", layoutId);
		startActivity(intent);
	}

}