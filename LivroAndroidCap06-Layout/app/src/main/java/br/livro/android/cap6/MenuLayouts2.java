package br.livro.android.cap6;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.livro.android.cap6.layout.ExemploGallery;
import br.livro.android.cap6.layout.ExemploGridView;
import br.livro.android.cap6.layout.ExemploImageSwitcher;
import br.livro.android.cap6.layout.ExemploViewFlipper;
import br.livro.android.cap6.layout.ExemploWebView;
import br.livro.android.cap6.layout.tab.ExemploTabHost;

public class MenuLayouts2 extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] items = new String[] { 
				"GridView",
				"Gallery",
				"ImageSwitcher",
				"TabHost",
				"WebView",
				"ViewFlipper"
				};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this,ExemploGridView.class));
				break;
			case 1:
				startActivity(new Intent(this,ExemploGallery.class));
				break;
			case 2:
				startActivity(new Intent(this,ExemploImageSwitcher.class));
				break;
			case 3:
				startActivity(new Intent(this,ExemploTabHost.class));
				break;
			case 4:
				startActivity(new Intent(this,ExemploWebView.class));
				break;
			case 5:
				startActivity(new Intent(this,ExemploViewFlipper.class));
				break;
			default:
				finish();
				break;
		}
	}
}