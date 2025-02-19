package com.ufvmobile.pvanet.domain.model;

/**
 * The type of a {@link Tool}
 */
public class ToolType {

    /*the reason why the variables names are in Portuguese it's because we need them to matches the
    API response*/
    int codigo;
    String descricao;

    public ToolType(int id, String description) {
        this.codigo = id;
        this.descricao = description;
    }

    //TODO: Javadoc para métodos

    public int getId() {
        return codigo;
    }

    public String getDescription() {
        return descricao;
    }
}

