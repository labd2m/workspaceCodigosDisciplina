package br.ufv.dpi.aulanavegacao;

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

        Bundle bd = it.getExtras();

        TextView txtNome = findViewById(R.id.txtNome);
        TextView txtEmail = findViewById(R.id.txtEmail);
        TextView txtSexo = findViewById(R.id.txtSexo);

        /*txtNome.setText("Nome: " + bd.getString("nome"));
        txtEmail.setText("Email: " + bd.getString("email"));
        txtSexo.setText("Sexo: " + bd.getString("sexo"));*/

        txtNome.setText("Nome: " + it.getStringExtra("nome"));
        txtEmail.setText("Email: " + it.getStringExtra("email"));
        txtSexo.setText("Sexo: " + it.getStringExtra("sexo"));

    }
}
