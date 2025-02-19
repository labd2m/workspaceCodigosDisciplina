package com.example.bancodadosexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public final class BancoDadosSingleton {

	private SQLiteDatabase db;
	private static BancoDadosSingleton INSTANCE;
	private final String NOME_BANCO = "exemplo_bd_singleton";
	private final String[] SCRIPT_DATABASE_CREATE = new String[] {
		"CREATE TABLE Autor (idAutor INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, tel TEXT);",
		"CREATE TABLE Livro (idLivro INTEGER PRIMARY KEY AUTOINCREMENT, idAutor INTEGER NOT NULL, nome TEXT NOT NULL, edicao INTEGER NOT NULL, CONSTRAINT fkey0 FOREIGN KEY (idAutor) REFERENCES Autor (idAutor));",
		"INSERT INTO Autor (nome, tel) VALUES ('Lucas', '37210000');",
		"INSERT INTO Autor (nome, tel) VALUES ('Aline', '37280101');",
		"INSERT INTO Livro (idAutor, nome, edicao) VALUES (1, 'Android Avançado', 1);",
		"INSERT INTO Livro (idAutor, nome, edicao) VALUES (1, 'Android Básico', 3);",
		"INSERT INTO Livro (idAutor, nome, edicao) VALUES (2, 'Nutrição Infantil', 2);"};

	private BancoDadosSingleton() {
		Context ctx = MyApp.getAppContext();

		// Abre o banco de dados já existente ou então cria um banco novo
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
		
		//busca por tabelas existentes no banco = "show tables" do MySQL
		//SELECT * FROM sqlite_master WHERE type = "table"
		Cursor c = buscar("sqlite_master", null, "type = 'table'", "");

		
		//Cria tabelas do banco de dados caso o mesmo estiver vazio.
		//bancos criados pelo método openOrCreateDatabase() possuem uma tabela padrão "android_metadata"
		if(c.getCount() == 1){
			for(int i = 0; i < SCRIPT_DATABASE_CREATE.length; i++){
				db.execSQL(SCRIPT_DATABASE_CREATE[i]);
			}
			Log.i("BANCO_DADOS", "Criou tabelas do banco e as populou.");
		}

		//Verifica o nome das tabelas criadas no banco.
		while(c.moveToNext()){
			int name = c.getColumnIndex("name");

			Log.i("BANCO_DADOS",c.getString(name));
		}
		
		c.close();		
		Log.i("BANCO_DADOS", "Abriu conexão com o banco.");
	}

	public static BancoDadosSingleton getInstance(){
		if (BancoDadosSingleton.INSTANCE == null){
			BancoDadosSingleton.INSTANCE = new BancoDadosSingleton();
		}
		//abre conexão caso esteja fechada
		BancoDadosSingleton.INSTANCE.abrir();

		return BancoDadosSingleton.INSTANCE;
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
	private void abrir() {
		Context ctx = MyApp.getAppContext();

		if(!db.isOpen()) {
			// Abre o banco de dados já existente
			db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
			Log.i("BANCO_DADOS", "Abriu conexão com o banco.");
		}else {
			Log.i("BANCO_DADOS", "Conexão com o banco já estava aberta.");
		}
	}
	
	// Fecha o banco
	public void fechar() {
		// fecha o banco de dados
		if (db != null && db.isOpen()) {
			db.close();
			Log.i("BANCO_DADOS", "Fechou conexão com o Banco.");
		}
	}
}
