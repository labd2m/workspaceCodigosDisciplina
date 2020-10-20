package br.livro.android.cap3;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class Exemplo2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo2);
        
        //RECUPERAÇÃO DE ID DE IMAGEM DINÂMICA
        ImageView img1 = (ImageView) findViewById(R.id.imageView1) ;
        img1.setImageResource(R.drawable.ic_launcher);
        
      //RECUPERAÇÃO DE ID DO CANPO TEXTO DINÂMICO
        TextView txt = (TextView) findViewById(R.id.textView1);
        txt.setText("Yeah!");

        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_exemplo2, menu);
        return true;
    }

    
}
