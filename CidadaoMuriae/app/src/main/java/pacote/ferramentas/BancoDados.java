package pacote.ferramentas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BancoDados {
	
	protected SQLiteDatabase db;
	private final String NOME_BANCO = "cidadao_bd";
	private final String[] SCRIPT_DATABASE_CREATE = new String[] {
		"CREATE TABLE colaboracao ("+
			  "id INTEGER PRIMARY KEY AUTOINCREMENT,"+ 
			  "titulo TEXT NOT NULL,"+ 
			  "resumo TEXT NOT NULL,"+ 
			  "categoria TEXT NOT NULL,"+ 
			  "subcategoria TEXT NOT NULL,"+ 
			  "foto TEXT,"+ 
			  "video TEXT,"+ 
			  "urifoto TEXT,"+ 
			  "urivideo TEXT,"+ 
			  "latitude TEXT NOT NULL,"+ 
			  "longitude TEXT NOT NULL,"+ 
			  "idusuario TEXT NOT NULL,"+ 
			  "dataocorrencia TEXT NOT NULL,"+ 
			  "horaocorrencia TEXT NOT NULL,"+ 
			  "sync INTEGER NOT NULL DEFAULT 0);"};

	public BancoDados(Context ctx) {
		// Abre o banco de dados já existente ou então cria um banco novo
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
		
		//busca por tabelas existentes no banco = "show tables" do MySQL
		//SELECT * FROM sqlite_master WHERE type = "table"
		Cursor c = buscar("sqlite_master", null, "type = 'table'", "");
		
		//Cria tabelas do banco de dados caso o mesmo estiver vazio.
		//Todo banco criado pelo método openOrCreateDatabase() possui uma tabela padrão "android_metadata"
		if(c.getCount() == 1){
			for(int i = 0; i < SCRIPT_DATABASE_CREATE.length; i++){
				db.execSQL(SCRIPT_DATABASE_CREATE[i]);
			}
			Log.i("BANCO_DADOS", "Criou tabelas do banco e as populou.");
		}
		
		c.close();		
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
