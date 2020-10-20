package lucas.vegi.helloworld;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.widget.Toast;

public class Tela2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tela2);
        
        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        
        //RECUPERA OBJETO DE CLASSE QUE IMPLEMENTA SERIALIZABLE
        Pessoa p = (Pessoa) it.getSerializableExtra("objetoPessoa");
        Carro c = (Carro) bundle.getSerializable("carro");

        TextView txt = (TextView) findViewById(R.id.textViewValor);
        txt.setText(it.getIntExtra("valor1",0) + it.getStringExtra("op") + it.getIntExtra("valor2",0) + " = " + it.getIntExtra("resultado",0));

        try {
            //Exibe valor vindo via Bundle
            Log.i("ValorBundle", "Cheguei! " + bundle.getString("teste"));
            Log.i("ValorBundle", "Nome: " + c.getNome() + " Ano: " + c.getAno() + " Placa: " + c.getPlaca());

            //Exibe valor de objeto enviado via Intent
            Toast.makeText(this, "- Valores Intent -" +
                                "\nNome: " + p.getNome() +
                                "\nCpf: " + p.getCpf() +
                                "\nIdade: " + p.getIdade() +
                                "\nSexo: " + p.getSexo() +
                                "\nPeso: " + p.getPeso(),Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERRO", e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_tela2, menu);
        return true;
    }
}



