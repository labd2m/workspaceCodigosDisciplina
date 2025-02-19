package com.ufvmobile.pvanet.domain.model;

/**
 * Represent a single tool of a {@link Module}
 */
public class Tool implements Comparable {

	/*the reason why the variables names are in Portuguese it's because we need them to matches the
    API response*/
	private int codigoFerramenta;
	private int codigoModulo;
	private int ordenacao;
	ToolType tipoFerramenta;
	String tituloFerramenta;

    public Tool(int id, int moduleId, int order, ToolType type, String title) {
        this.codigoFerramenta = id;
        this.codigoModulo = moduleId;
        this.ordenacao = order;
        this.tipoFerramenta = type;
        this.tituloFerramenta = title;
    }

    //TODO: Javadoc para métodos

    public int getId() {
		return this.codigoFerramenta;
	}

	public int getModuleId() {
		return this.codigoModulo;
	}


	public int getOrder() {
		return this.ordenacao;
	}

	public ToolType getType() {
		return tipoFerramenta;
	}

	public String getTitle() {
		return tituloFerramenta;
	}


	@Override
	public int compareTo(Object another) {

		Tool tool = (Tool) another;
		return this.getOrder() - tool.getOrder();

	}


}