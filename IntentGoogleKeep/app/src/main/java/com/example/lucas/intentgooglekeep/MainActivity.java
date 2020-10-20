package com.example.lucas.intentgooglekeep;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Salvar(View v){
        EditText edtLocal = (EditText) findViewById(R.id.edtLocal);
        EditText edtLembrete = (EditText) findViewById(R.id.edtLembrete);

        try {
            //Intent it = new Intent(Intent.ACTION_SEND);
            //it.setType("text/plain");

            Intent it = new Intent(Intent.ACTION_INSERT);
            it.setType("vnd.android.cursor.item/vnd.google.memory.note");
            it.setPackage("com.google.android.keep");

            it.putExtra(Intent.EXTRA_SUBJECT, edtLocal.getText().toString());
            it.putExtra(Intent.EXTRA_TEXT, edtLembrete.getText().toString() + "\nAutor: Lucas\nData: Maio 2017");

            startActivity(it);
        }catch (Exception e){
            Toast.makeText(this,"Google Keep não está instalado!",Toast.LENGTH_SHORT).show();
        }
    }
}
