package br.livro.android.cap14.banco;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import br.livro.android.cap14.banco.Carro.Carros;

/**
 * <pre>
 * Repositório para carros que utiliza o SQLite internamente
 * 
 * Para visualizar o banco pelo adb shell:
 * 
 * &gt;&gt; sqlite3 /data/data/br.livro.android.exemplos.banco/databases/BancoCarro
 * 
 * &gt;&gt; Mais info dos comandos em: http://www.sqlite.org/sqlite.html
 * 
 * &gt;&gt; .exit para sair
 * 
 * </pre>
 * 
 * @author rlecheta
 * 
 */
public class RepositorioCarro {
	private static final String CATEGORIA = "livro";

	// Nome do banco
	private static final String NOME_BANCO = "livro_android";
	// Nome da tabela
	public static final String NOME_TABELA = "carro";

	protected SQLiteDatabase db;

	public RepositorioCarro(Context ctx) {
		// Abre o banco de dados já existente
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	protected RepositorioCarro() {
		// Apenas para criar uma subclasse...
	}

	// Salva o carro, insere um novo ou atualiza
	public long salvar(Carro carro) {
		long id = carro.id;

		if (id != 0) {
			atualizar(carro);
		} else {
			// Insere novo
			id = inserir(carro);
		}

		return id;
	}

	// Insere um novo carro
	public long inserir(Carro carro) {
		ContentValues values = new ContentValues();
		values.put(Carros.NOME, carro.nome);
		values.put(Carros.PLACA, carro.placa);
		values.put(Carros.ANO, carro.ano);

		long id = inserir(values);
		return id;
	}

	// Insere um novo carro
	public long inserir(ContentValues valores) {
		long id = db.insert(NOME_TABELA, "", valores);
		return id;
	}

	// Atualiza o carro no banco. O id do carro é utilizado.
	public int atualizar(Carro carro) {
		ContentValues values = new ContentValues();
		values.put(Carros.NOME, carro.nome);
		values.put(Carros.PLACA, carro.placa);
		values.put(Carros.ANO, carro.ano);

		String _id = String.valueOf(carro.id);

		String where = Carros._ID + "=?";
		String[] whereArgs = new String[] { _id };

		int count = atualizar(values, where, whereArgs);

		return count;
	}

	// Atualiza o carro com os valores abaixo
	// A cláusula where é utilizada para identificar o carro a ser atualizado
	public int atualizar(ContentValues valores, String where, String[] whereArgs) {
		int count = db.update(NOME_TABELA, valores, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
		return count;
	}

	// Deleta o carro com o id fornecido
	public int deletar(long id) {
		String where = Carros._ID + "=?";

		String _id = String.valueOf(id);
		String[] whereArgs = new String[] { _id };

		int count = deletar(where, whereArgs);

		return count;
	}

	// Deleta o carro com os argumentos fornecidos
	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		Log.i(CATEGORIA, "Deletou [" + count + "] registros");
		return count;
	}

	// Busca o carro pelo id
	public Carro buscarCarro(long id) {
		// select * from carro where _id=?
		Cursor c = db.query(true, NOME_TABELA, Carro.colunas, Carros._ID + "=" + id, null, null, null, null, null);

		if (c.getCount() > 0) {

			// Posicinoa no primeiro elemento do cursor
			c.moveToFirst();

			Carro carro = new Carro();

			// Lê os dados
			carro.id = c.getLong(0);
			carro.nome = c.getString(1);
			carro.placa = c.getString(2);
			carro.ano = c.getInt(3);

			return carro;
		}

		return null;
	}

	// Retorna um cursor com todos os carros
	public Cursor getCursor() {
		try {
			// select * from carros
			return db.query(NOME_TABELA, Carro.colunas, null, null, null, null, null, null);
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os carros: " + e.toString());
			return null;
		}
	}

	// Retorna uma lista com todos os carros
	public List<Carro> listarCarros() {
		Cursor c = getCursor();

		List<Carro> carros = new ArrayList<Carro>();

		if (c.moveToFirst()) {

			// Recupera os índices das colunas
			int idxId = c.getColumnIndex(Carros._ID);
			int idxNome = c.getColumnIndex(Carros.NOME);
			int idxPlaca = c.getColumnIndex(Carros.PLACA);
			int idxAno = c.getColumnIndex(Carros.ANO);

			// Loop até o final
			do {
				Carro carro = new Carro();
				carros.add(carro);

				// recupera os atributos de carro
				carro.id = c.getLong(idxId);
				carro.nome = c.getString(idxNome);
				carro.placa = c.getString(idxPlaca);
				carro.ano = c.getInt(idxAno);

			} while (c.moveToNext());
		}

		return carros;
	}

	// Busca o carro pelo nome "select * from carro where nome=?"
	public Carro buscarCarroPorNome(String nome) {
		Carro carro = null;

		try {
			// Idem a: SELECT _id,nome,placa,ano from CARRO where nome = ?
			Cursor c = db.query(NOME_TABELA, Carro.colunas, Carros.NOME + "='" + nome + "'", null, null, null, null);

			// Se encontrou...
			if (c.moveToNext()) {

				carro = new Carro();

				// utiliza os métodos getLong(), getString(), getInt(), etc para recuperar os valores
				carro.id = c.getLong(0);
				carro.nome = c.getString(1);
				carro.placa = c.getString(2);
				carro.ano = c.getInt(3);
			}
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar o carro pelo nome: " + e.toString());
			return null;
		}

		return carro;
	}

	// Busca um carro utilizando as configurações definidas no
	// SQLiteQueryBuilder
	// Utilizado pelo Content Provider de carro
	public Cursor query(SQLiteQueryBuilder queryBuilder, String[] projection, String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy) {
		Cursor c = queryBuilder.query(this.db, projection, selection, selectionArgs, groupBy, having, orderBy);
		return c;
	}

	// Fecha o banco
	public void fechar() {
		// fecha o banco de dados
		if (db != null) {
			db.close();
		}
	}
}
