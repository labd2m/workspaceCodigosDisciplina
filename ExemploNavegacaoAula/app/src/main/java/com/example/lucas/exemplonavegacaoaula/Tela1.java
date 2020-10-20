package com.example.lucas.exemplonavegacaoaula;

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
        setContentView(R.layout.activity_tela1);
    }

    public void navegar(View v){
        EditText edtNome = (EditText) findViewById(R.id.edtNome);
        EditText edtIdade = (EditText) findViewById(R.id.edtIdade);

        Intent it = new Intent(this, Tela2.class);
        Bundle dados = new Bundle();
        dados.putString("nome",edtNome.getText().toString());
        dados.putInt("idade",Integer.parseInt(edtIdade.getText().toString()));
        it.putExtras(dados);

        it.putExtra("nome",edtNome.getText().toString());
        it.putExtra("idade",Integer.parseInt(edtIdade.getText().toString()));

        startActivity(it);
    }
}
