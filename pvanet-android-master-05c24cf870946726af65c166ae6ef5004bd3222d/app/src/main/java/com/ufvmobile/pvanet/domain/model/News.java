package com.ufvmobile.pvanet.domain.model;

/**
 * Represents a single news of the student
 */
public class News {

    /*the reason why the variables names are in Portuguese it's because we need them to matches the
    API response*/
	private int codigo;
	private String dataFim;
	private String dataInicio;
	private String exibir;
	private String noticia;
	private String resumo;
	private String titulo;

    //TODO: Javadoc para métodos

	public int getId() {
		return this.codigo;
	}

	public String getEndDate() {
		return this.dataFim;
	}

	public String getStartDate() {
		return this.dataInicio;
	}

	public String getShow() {
		return this.exibir;
	}

	public String getNews() {
		return this.noticia;
	}

	public String getResume() {
		return this.resumo;
	}

	public String getTitle() {
		return this.titulo;
	}
}