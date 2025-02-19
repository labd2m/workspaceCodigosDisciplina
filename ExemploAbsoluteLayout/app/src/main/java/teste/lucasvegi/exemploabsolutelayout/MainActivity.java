package teste.lucasvegi.exemploabsolutelayout;

import android.app.Activity;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {
    public int dimenX;  //dimensao horizontal da tela em pixel
    public int dimenY;  //dimensao vertical da tela em pixel
    public float centerX;   //centro horizontal ajustado
    public float centerY;   //centro vertical ajustado
    public float larguraImgPokemon = 0;
    public float alturaImgPokemon = 0;
    public ImageView pkmn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recupera referência da view
        pkmn = (ImageView) findViewById(R.id.pokemon);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //garante só rodar após as views estarem na tela
        pkmn.post(new Runnable() {
            @Override
            public void run() {
                //Obtem a resolução da tela
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                dimenX = size.x;    //largura da tela em pixel
                dimenY = size.y;    //altura da tela em pixel

                //define largura do pokemon em relação ao tamanho da tela
                larguraImgPokemon = (float)(dimenX * 0.5);
                //obtem propoção da imagem redimensionada - regra de três
                float proporcaoPokemon = (larguraImgPokemon * 100) / pkmn.getMeasuredWidth();
                //define altura do pokemon de forma proporcional
                alturaImgPokemon = pkmn.getMeasuredHeight() * proporcaoPokemon / 100;

                //obtem o centro da tela
                centerX = dimenX / 2 - (((int) larguraImgPokemon) / 2);
                centerY = dimenY / 2 - (((int) alturaImgPokemon) / 2);

                //modifica o tamanho da imagem e centraliza o mesmo na tela
                AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams((int) larguraImgPokemon, (int) alturaImgPokemon, (int) centerX, (int) centerY);
                pkmn.setLayoutParams(params);
            }
        });
    }

    public void clickX(View v){
        pkmn.setX(pkmn.getX() + 10); //desloca a imagem em 10 pixel
    }

    public void clickY(View v){
        pkmn.setY(pkmn.getY() + 10); //desloca a imagem em 10 pixel
    }
}
