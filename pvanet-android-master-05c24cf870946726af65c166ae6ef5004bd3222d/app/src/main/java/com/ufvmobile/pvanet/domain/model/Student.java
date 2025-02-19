package com.ufvmobile.pvanet.domain.model;

import java.io.Serializable;

/**
 * Represents the student
 */
public class Student implements Serializable {

    /*the reason why the variables names are in Portuguese it's because we need them to matches the
    API response*/
	private int codigoUsuario;
	private String email;
    private String emailUFV;
	private String matriculaSapiens;
	private String nome;
	private String password;

    //TODO: Javadoc para métodos

	public int getId() {
		return this.codigoUsuario;
	}

	public String getEmail() {
		return this.email;
	}

    public String getEmailUfv() {
        return emailUFV;
    }

	public String getSapiensRegistrationNumber() {
		return this.matriculaSapiens;
	}

	public String getName() {
		return this.nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMatriculaSapiens(String mat) {
		this.matriculaSapiens = mat;
	}

}