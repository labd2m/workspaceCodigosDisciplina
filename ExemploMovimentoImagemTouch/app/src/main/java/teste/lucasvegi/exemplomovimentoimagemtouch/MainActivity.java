package teste.lucasvegi.exemplomovimentoimagemtouch;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    public ImageView pokebola;
    public float larguraImgPokeball = 0;
    public float alturaImgPokeball = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Recupera referências da view
        pokebola = (ImageView) findViewById(R.id.imgPokebola);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //garante só rodar após as views estarem na tela
        pokebola.post(new Runnable() {
            @Override
            public void run() {
                //Obtem dimensões da pokebola
                larguraImgPokeball = pokebola.getMeasuredWidth();
                alturaImgPokeball = pokebola.getMeasuredHeight();
                configuraTouchPokebola();
            }
        });
    }

    public void configuraTouchPokebola(){
        //configura o listener de toque na pokebola
        pokebola.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //obtem local do toque na tela
                float x = event.getRawX();
                float y = event.getRawY();
                Log.d("MoverPokebola", "X: " + x + " Y: " + y);

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        //tratamento se pressionou a imagem
                        Toast.makeText(getBaseContext(),"Tocou imagem",Toast.LENGTH_SHORT).show();
                        Log.d("EventoPokebola", "Tocou na pokebola");
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        //tratamento se retirou o dedo da imagem
                        Toast.makeText(getBaseContext(),"Soltou a imagem",Toast.LENGTH_SHORT).show();
                        Log.d("EventoPokebola", "Retirou o dedo da pokebola");
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        //tratamento para arrastar imagem
                        pokebola.setX( ((int) x) - (larguraImgPokeball / 2));
                        pokebola.setY( ((int) y) - (alturaImgPokeball / 2));
                        Log.d("EventoPokebola", "Moveu a pokebola");
                        return true;
                }
                return false;
            }
        });
    }
}
