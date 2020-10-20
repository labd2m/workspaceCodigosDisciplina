package com.example.lucas.exemplonavegacaoaula;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Tela2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        Intent it = getIntent();
        Bundle dados = it.getExtras();

        TextView txtNome = (TextView) findViewById(R.id.txtNome);
        TextView txtIdade = (TextView) findViewById(R.id.txtIdade);

        //txtNome.setText("Nome: " + dados.getString("nome"));
        //txtIdade.setText("Idade: " + dados.getInt("idade"));

        txtNome.setText("Nome: " + it.getStringExtra("nome"));
        txtIdade.setText("Idade: " + it.getIntExtra("idade",0));
    }
}
