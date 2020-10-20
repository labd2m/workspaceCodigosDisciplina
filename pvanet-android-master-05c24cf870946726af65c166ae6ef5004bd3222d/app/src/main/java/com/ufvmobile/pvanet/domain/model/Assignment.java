package com.ufvmobile.pvanet.domain.model;

/**
 * Represents a single Assignment of a Student
 */
@Deprecated
public class Assignment {

    /*the reason why the variables names are in Portuguese it's because we need them to matches the
    API response*/
	private int codigo;
	private String dataEntrega;
	private String titulo;

	public Assignment(int id, String deadline, String title) {
		this.codigo = id;
		this.dataEntrega = deadline;
		this.titulo = title;
	}

    //TODO: Javadoc para métodos

	public int getId() {
		return this.codigo;
	}

	public String getDeadline() {
		return this.dataEntrega;
	}


	public String getTitle() {
		return this.titulo;
	}

}