package com.example.lucas.serializablecompareexample;

import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 02/12/2016.
 */
public class Pokemon implements Serializable{
    private int numero;
    private String nome;
    private String categoria;
    private int foto;
    private int icone;
    private List<Tipo> tipos;

    public Pokemon(){

    }

    public Pokemon(int numero, String nome, String categoria, int foto, int icone, List<Tipo> tipos){
        this.numero = numero;
        this.nome = nome;
        this.categoria = categoria;
        this.foto = foto;
        this.icone = icone;
        this.tipos = tipos;

    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getIcone() {
        return icone;
    }

    public void setIcone(int icone) {
        this.icone = icone;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            //Verificando se o segundo participante está nulo
            if (obj == null)
                return false;

            //verifica se são da mesma classe
            if (this.getClass() != obj.getClass())
                return false;

            //verifica se ocupam o mesmo lugar na memória
            if (super.equals(obj))
                return true;

            Pokemon pkmn = (Pokemon) obj;

            //Compara os dois objetos pelo estado interno
            if(this.getNumero() == pkmn.getNumero())
                return true;
            else
                return false;

        }catch (Exception e){
            return false;
        }
    }

    @Override
    public int hashCode() {
        //geração própria da hashCode para evitar colisão - objetos de classes diferentes com o mesmo hashCode
        //evita também que NÃO se retorne o mesmo hashCode para o mesmo objeto
        try {
            int numPrimo = 17;
            int hash = 1;

            //TÉCNICA: somar os hashCodes de todos os atributos da classe e multiplicar por um número primo
            hash = numPrimo * hash + ((this.nome == null) ? 0 : this.nome.hashCode());
            hash = numPrimo * hash + ((this.categoria == null) ? 0 : this.categoria.hashCode());
            hash = numPrimo * hash + (this.numero);
            hash = numPrimo * hash + (this.foto);
            hash = numPrimo * hash + (this.icone);
            hash = numPrimo * hash + (this.tipos.get(0).getIdTipo());
            hash = numPrimo * hash + ((this.tipos.get(0).getNome() == null) ? 0 : this.tipos.get(0).getNome().hashCode());
            if (this.tipos.size() > 1){
                hash = numPrimo * hash + (this.tipos.get(1).getIdTipo());
                hash = numPrimo * hash + ((this.tipos.get(1).getNome() == null) ? 0 : this.tipos.get(1).getNome().hashCode());
            }

            return hash;
        }catch (Exception e){
            return super.hashCode();
        }
    }
}
