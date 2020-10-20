package br.livro.android.cap6.layout.tab;

import br.livro.android.cap6.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;

/**
 * TabActivity deprecated ?
 * http://groups.google.com/group/android-developers/browse_thread
 * /thread/61976a6a2d86673d
 * 
 * @author ricardo
 * 
 */
public class ExemploTabHost extends TabActivity implements OnTabChangeListener {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		TabHost tabHost = getTabHost(); //inicia a TabHost
		tabHost.setOnTabChangedListener(this);//configura método de clique

		// Cria Aba 1
		TabSpec tab1 = tabHost.newTabSpec("Tab 1"); //cria uma nova aba
		//Informa texto e icone da nova aba
		tab1.setIndicator("ABA 1", getResources().getDrawable(R.drawable.smile1));
		tab1.setContent(new Intent(this, Aba1.class)); //informa pra onde ela direciona
		tabHost.addTab(tab1); //adiciona a aba no TabHost

		// Cria Aba 2
		TabSpec tab2 = tabHost.newTabSpec("Tab 2");
		tab2.setIndicator("ABA 2", getResources().getDrawable(R.drawable.smile2));
		tab2.setContent(new Intent(this, Aba2.class));
		tabHost.addTab(tab2);
	}

	//Ação que acontece quando muda de Aba
	public void onTabChanged(String tabId) {
		Log.i("Trocas", "Trocou aba: " + tabId);
	}
}