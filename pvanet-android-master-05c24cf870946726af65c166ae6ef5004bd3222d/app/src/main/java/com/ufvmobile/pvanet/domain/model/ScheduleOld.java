package com.ufvmobile.pvanet.domain.model;

/**
 * represents a single item of the student ScheduleOld
 */

@Deprecated
public class ScheduleOld {

    /*the reason why the variables names are in Portuguese it's because we need them to matches the
    API response*/
	private int codigo;
	private String data;
	private String descricao;
	private boolean exibir;

    //TODO: Javadoc para métodos

	public int getId() {
		return this.codigo;
	}

	public String getDate() {
		return this.data;
	}

	public String getDescription() {
		return this.descricao;
	}

	public void setShow(boolean show) {
		this.exibir = show;
	}
}