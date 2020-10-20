package com.example.lucas.testeparametrosentretelas;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Tela1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tela1);
    }

    public void enviar(View v){
        EditText edtNome = (EditText) findViewById(R.id.edtNome);
        EditText edtIdade = (EditText) findViewById(R.id.edtIdade);

        String nome = edtNome.getText().toString();
        int idade = Integer.parseInt(edtIdade.getText().toString());

        Bundle mochila = new Bundle();

        mochila.putString("nome", nome);
        mochila.putInt("idade", idade);

        Intent it = new Intent(this,Tela2.class);
        it.putExtras(mochila);

        startActivity(it);
        finish();
    }
}
