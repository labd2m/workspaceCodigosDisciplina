package br.livro.android.cap6;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] items = new String[] { "Layouts Básico","Layouts","Outros ViewGroup"};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this,MenuBasicoLayouts.class));
				break;
			case 1:
				//Aplicação estilo File Explorer para navegar nas pastas
				startActivity(new Intent(this,MenuLayouts.class));
				break;
			case 2:
				startActivity(new Intent(this,MenuLayouts2.class));
				break;
		}
	}

}