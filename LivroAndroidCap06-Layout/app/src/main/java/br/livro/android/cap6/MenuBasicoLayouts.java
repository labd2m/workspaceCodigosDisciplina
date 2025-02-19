package br.livro.android.cap6;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplo demonstrando a utilização do wrap_content e fill_parent
 * 
 * @author ricardo
 *
 */
public class MenuBasicoLayouts extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] items = new String[] { "wrap_content","fill_parent","wrap_content img","fill_parent img wrap_content","fill_parent img fill_parent"};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(R.layout.frame_layout_wrap_content);
				break;
			case 1:
				startActivity(R.layout.frame_layout_fill_parent);
				break;
			case 2:
				startActivity(R.layout.frame_layout_wrap_content_img);
				break;
			case 3:
				startActivity(R.layout.frame_layout_fill_parent_img_wrap);
				break;
			case 4:
				startActivity(R.layout.frame_layout_fill_parent_img_fill);
				break;
		}
	}

	private void startActivity(int layoutId) {
		Intent intent = new Intent(this,ActivityLayoutIdGenerica.class);
		intent.putExtra("layoutResId", layoutId);
		startActivity(intent);
	}

}