package com.example.bancodadosexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class PrincipalBD extends Activity {

    //public BancoDados bd;
    //public SimpleBancoDados bd;
    //public SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_princial_bd);

        //COMENTAR PARA EXEMPLO DE CRIAÇÃO ESTÁTICA DO BD
        //bd = new BancoDados(this);

        //COMENTAR PARA EXEMPLO DE CRIAÇÃO DINÂMICA DO BD
        //db = this.openOrCreateDatabase("bdTeste", Context.MODE_PRIVATE, null);

        //USADO NA CRIAÇÃO MANIPULAÇÃO DE BANCO DE DADOS CRIADO ESTATICAMENTE
        //bd = new SimpleBancoDados(this);
    }

    public void Mostrar(View v){
        //------EXEMPLO USANDO SINGLETON------
        Cursor c = BancoDadosSingleton.getInstance().buscar("Autor", new String[]{"idAutor","nome","tel"}, "", "");

        //COMENTAR PARA EXEMPLO DE CRIAÇÃO DINÂMICA DO BD
        //Cursor c = db.query("Autor", new String[]{"idAutor","nome","tel"}, null, null, null, null, null);

        //COMENTAR PARA EXEMPLO DE CRIAÇÃO ESTÁTICA DO BD
        //Cursor c = bd.buscar("Autor", new String[]{"idAutor","nome","tel"}, "", "");

        String aux = "";

        while(c.moveToNext()){
            int idA = c.getColumnIndex("idAutor");
            int name = c.getColumnIndex("nome");
            int tel = c.getColumnIndex("tel");

            aux += "ID:" + c.getInt(idA) + "\nNOME: " + c.getString(name) + "\nTEL: " + c.getString(tel) + "\n\n";
        }

        TextView txt = (TextView) findViewById(R.id.txtPrincipal);
        txt.setText(aux);

        c.close();
    }

    public void Cadastrar(View v){

        Intent it = new Intent(this,CadastrarAutor.class);
        startActivity(it);
        	
    	/*
    	 * -----------------------------------EXEMPLO BUSCAR REGISTROS-------------------------------------
    	 * 
    	 	Cursor c = bd.buscar("Autor a, Livro l", new String[]{"l.idLivro id","l.nome nomeLivro","a.nome nomeAutor"}, "a.idAutor = l.idAutor AND a.nome = 'Lucas'", "l.nome desc");
			String aux= "";
        	
			if(c.getCount() > 0){
	        	
        		while(c.moveToNext()){
	        		int idL = c.getColumnIndex("id");
	        		int nameLivro = c.getColumnIndex("nomeLivro");
	        		int nameAuthor = c.getColumnIndex("nomeAutor");
	        		
	        		aux += "ID LIVRO:" + c.getInt(idL) + "\nNOME LIVRO: " + c.getString(nameLivro) + "\nNOME AUTOR: " + c.getString(nameAuthor) + "\n\n";	        	
	        	}
	        	
	        	TextView txt = (TextView) findViewById(R.id.txtPrincipal);	        	
	        	txt.setText(aux);
	        	c.close();
			}
			
    	 * -----------------------------------EXEMPLO INSERIR REGISTROS-------------------------------------
    	 
    	ContentValues valores = new ContentValues();
    	valores.put("nome", "Tolkien");
    	valores.put("tel", "1111-2020");
    	
    	bd.inserir("Autor", valores);   	
    	
    	 * -----------------------------------EXEMPLO ATUALIZAR REGISTROS-------------------------------------
    	 
    	ContentValues valores = new ContentValues();
    	valores.put("nome", "Bill Gates");
    	valores.put("tel", "9999-9999");
    	
    	bd.atualizar("Autor", valores, "nome = 'Tolkien'");
    	
    	 * -----------------------------------EXEMPLO DELETAR REGISTROS-------------------------------------
    	
    	bd.deletar("Autor", "nome = 'Tolkien'");
    	*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //db.close();
        //bd.fechar();
        BancoDadosSingleton.getInstance().fechar();
    }

}
