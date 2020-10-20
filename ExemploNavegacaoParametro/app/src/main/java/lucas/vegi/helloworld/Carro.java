package lucas.vegi.helloworld;

import java.io.Serializable;

public class Carro implements Serializable {
    private String nome;
    private int ano;
    private String placa;

    public Carro(String nome, int ano, String placa) {
        this.nome = nome;
        this.ano = ano;
        this.placa = placa;
    }

    public Carro() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
