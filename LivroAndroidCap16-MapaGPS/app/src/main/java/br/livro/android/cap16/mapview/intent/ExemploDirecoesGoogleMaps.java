package br.livro.android.cap16.mapview.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Exemplo que exibe as direções de um endereço ou coordenada para outra
 * 
 * Utiliza uma Intent para enviar a mensagem, e o Android faz o resto.
 * 
 * http://developer.android.com/guide/appendix/g-app-intents.html
 * 
 * @author ricardo
 *
 */
public class ExemploDirecoesGoogleMaps extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView t = new TextView(this);
        t.setText("Intent disparada.");
        setContentView(t);

        /**
         * http://developer.android.com/guide/appendix/g-app-intents.html
         * 
         * Google Maps - ACTION_VIEW
         * 
         * Opens the Maps application to the given location or query. The Geo URI scheme (not fully supported) is currently under development.
         * The z field specifies the zoom level. 
         * A zoom level of 1 shows the whole Earth, centered at the given lat,lng. 
         * A zoom level of 2 shows a quarter of the Earth, and so on. 
         * The highest zoom level is 23. 
         * A larger zoom level will be clamped to 23. 
         * 
         * Possíveis Intents
         * 
         * geo:latitude,longitude
         * geo:latitude,longitude?z=zoom
         * geo:0,0?q=my+street+address
         * geo:0,0?q=business+near+city
         */
        
        // Exibe uma coordenada no mapa
        
		String localizacao = "geo:-25.443195,-49.280977";
		startActivity(new Intent(Intent.ACTION_VIEW,  Uri.parse(localizacao)));
    }
}