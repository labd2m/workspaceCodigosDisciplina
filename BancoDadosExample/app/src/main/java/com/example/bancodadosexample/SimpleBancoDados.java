package com.example.bancodadosexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SimpleBancoDados {
	
	protected SQLiteDatabase db;
	private final String NOME_BANCO = "bdTeste";

	public SimpleBancoDados(Context ctx) {
		// Abre o banco de dados já existente ou então cria um banco novo
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);		
		Log.i("BANCO_DADOS", "Abriu conexão com o banco.");
	}

	// Insere um novo registro
	public long inserir(String tabela, ContentValues valores) {
		long id = db.insert(tabela, null, valores);
		
		Log.i("BANCO_DADOS", "Cadastrou registro com o id [" + id + "]");
		return id;
	}

	// Atualiza registros
	public int atualizar(String tabela, ContentValues valores, String where) {
		int count = db.update(tabela, valores, where, null);
		
		Log.i("BANCO_DADOS", "Atualizou [" + count + "] registros");
		return count;
	}

	// Deleta registros
	public int deletar(String tabela, String where) {
		int count = db.delete(tabela, where, null);
		
		Log.i("BANCO_DADOS", "Deletou [" + count + "] registros");
		return count;
	}

	// Busca registros
	public Cursor buscar(String tabela, String colunas[], String where, String orderBy) {
		Cursor c;
		if(!where.equals(""))
			c = db.query(tabela, colunas, where, null, null, null, orderBy);
		else
			c = db.query(tabela, colunas, null, null, null, null, orderBy);
		
		Log.i("BANCO_DADOS", "Realizou uma busca e retornou [" + c.getCount() + "] registros.");
		return c;
	}

	// Abre conexão com o banco
	public void abrir(Context ctx) {
		// Abre o banco de dados já existente
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
		Log.i("BANCO_DADOS", "Abriu conexão com o banco.");
	}
	
	// Fecha o banco
	public void fechar() {
		// fecha o banco de dados
		if (db != null) {
			db.close();
			Log.i("BANCO_DADOS", "Fechou conexão com o Banco.");
		}
	}
}
