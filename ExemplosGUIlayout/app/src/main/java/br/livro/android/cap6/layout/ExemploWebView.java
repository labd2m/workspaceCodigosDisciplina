package br.livro.android.cap6.layout;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Exemplo do GridView para visualizar imagens
 * 
 * @author rlecheta
 * 
 */
public class ExemploWebView extends Activity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		WebView web = new WebView(this);

		WebSettings webSettings = web.getSettings();
		webSettings.setSavePassword(false);
		webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);

		web.loadUrl("http://www.google.com.br");

		setContentView(web);
	}
}