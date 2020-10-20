package br.livro.android.cap7.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import br.livro.android.cap7.R;

public class ExemploImagem extends Activity {
     
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.exemplo_imagem);

        ImageView imgView = (ImageView)findViewById(R.id.imgid);
        imgView.setImageResource(R.drawable.balloons);

        imgView = (ImageView)findViewById(R.id.imgid2);
        imgView.setImageResource(R.drawable.icon);
   }
}