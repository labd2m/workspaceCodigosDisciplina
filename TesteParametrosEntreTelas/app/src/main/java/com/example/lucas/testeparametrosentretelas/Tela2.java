package com.example.lucas.testeparametrosentretelas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Tela2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tela2);

        Intent it = getIntent();
        Bundle mochila = it.getExtras();

        TextView txtNome = (TextView) findViewById(R.id.txtNome);
        TextView txtIdade = (TextView) findViewById(R.id.txtIdade);

        txtNome.setText(mochila.getString("nome"));
        txtIdade.setText(mochila.getInt("idade")+"");
    }
}
