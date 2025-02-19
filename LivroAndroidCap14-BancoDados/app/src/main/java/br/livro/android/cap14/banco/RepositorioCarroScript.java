package br.livro.android.cap14.banco;

import android.content.Context;

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
public class RepositorioCarroScript extends RepositorioCarro {

	// Script para fazer drop na tabela
	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS carro";

	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
			"create table carro ( _id integer primary key autoincrement, nome text not null,placa text not null,ano text not null);",
			"insert into carro(nome,placa,ano) values('Fusca A','ABC-1234',1950);",
			"insert into carro(nome,placa,ano) values('Brasilia B','DEF-5678',1960);",
			"insert into carro(nome,placa,ano) values('Chevete C','GHI-9999',1970);" };

	// Nome do banco
	private static final String NOME_BANCO = "livro_android";

	// Controle de versão
	private static final int VERSAO_BANCO = 1;

	// Nome da tabela
	public static final String TABELA_CARRO = "carro";

	// Classe utilitária para abrir, criar, e atualizar o banco de dados
	private SQLiteHelper dbHelper;

	// Cria o banco de dados com um script SQL
	public RepositorioCarroScript(Context ctx) {
		// Criar utilizando um script SQL
		dbHelper = new SQLiteHelper(ctx, RepositorioCarroScript.NOME_BANCO, RepositorioCarroScript.VERSAO_BANCO,
				RepositorioCarroScript.SCRIPT_DATABASE_CREATE, RepositorioCarroScript.SCRIPT_DATABASE_DELETE);

		// abre o banco no modo escrita para poder alterar também
		db = dbHelper.getWritableDatabase();
	}

	// Fecha o banco
	@Override
	public void fechar() {
		super.fechar();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}
