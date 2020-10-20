package com.example.bancodadosexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class CadastrarAutor extends Activity {

    //public SimpleBancoDados bd;
    //public BancoDados bd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_cadastar_autor);

        //COMENTAR PARA EXEMPLO DE CRIAÇÃO ESTÁTICA DO BD
        //bd = new BancoDados(this);

        //COMENTAR PARA EXEMPLO DE CRIAÇÃO DINÂMICA DO BD
        //bd = new SimpleBancoDados(this);
    }

    public void Cadastrar(View v){
        EditText edtNome = (EditText) findViewById(R.id.edtNome);
        EditText edtTel = (EditText) findViewById(R.id.edtTel);

        ContentValues valores = new ContentValues();
        valores.put("nome", edtNome.getText().toString());
        valores.put("tel", edtTel.getText().toString());

        //bd.inserir("Autor", valores);


        //------EXEMPLO USANDO SINGLETON------
        BancoDadosSingleton.getInstance().inserir("Autor", valores);

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //bd.fechar();

        //------EXEMPLO USANDO SINGLETON------
        BancoDadosSingleton.getInstance().fechar();
    }

}
