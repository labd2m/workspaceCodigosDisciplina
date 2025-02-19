package br.ufv.dpi.aulanavegacao;

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

    public void Enviar(View view){
        Intent it = new Intent(this, Tela2.class);

        Bundle bd = new Bundle();

        EditText edtNome = findViewById(R.id.edtNome);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtSexo = findViewById(R.id.edtSexo);

        bd.putString("nome", edtNome.getText().toString());
        bd.putString("email", edtEmail.getText().toString());
        bd.putString("sexo", edtSexo.getText().toString());

        it.putExtras(bd);

        it.putExtra("nome", edtNome.getText().toString());
        it.putExtra("email", edtEmail.getText().toString());
        it.putExtra("sexo", edtSexo.getText().toString());

        startActivity(it);
        finish();
    }
}
