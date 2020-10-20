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
public class ExemploDirecoes extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView t = new TextView(this);
        t.setText("Intent disparada.");
        setContentView(t);
        
//         String url = "http://maps.google.com/maps?f=d&saddr=37.4,-121.9&daddr=Bellevue, WA&hl=en";

        String url2 = "http://maps.google.com/maps?f=d&saddr=R. Carlos Benato, 5 - São Braz, Curitiba - PR, 82320-440&daddr=Av. Manoel Ribas, Curitiba, Brazil&hl=pt";

        String partida = "R. Carlos Benato, 5 - São Braz, Curitiba - PR, 82320-440";
        String destino = "Av. Manoel Ribas, Curitiba, Brazil";

        partida = "-25.443195, -49.280977 ";
        destino = "-25.442207, -49.278403";
        
        String url = "http://maps.google.com/maps?f=d&saddr="+partida+"&daddr="+destino+"&hl=pt";
        
		this.startActivity(new Intent(Intent.ACTION_VIEW,  Uri.parse(url)));  
    }
}