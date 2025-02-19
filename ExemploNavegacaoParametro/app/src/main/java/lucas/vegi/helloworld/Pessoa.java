package lucas.vegi.helloworld;

import java.io.Serializable;

public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private int idade;
	private int cpf;
	private String sexo;
	private double altura;
	private double peso;
		
	public Pessoa(String nome, int idade, int cpf, String sexo, double altura, double peso){
		setNome(nome);
		setIdade(idade);
		setCpf(cpf);
		setSexo(sexo);
		setAltura(altura);
		setPeso(peso);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

}
