package com.ufvmobile.pvanet.domain.model;

/**
 * Represents a single content of a {@link Topic}
 */
public class Content {

    /*the reason why the variables names are in Portuguese it's because we need them to matches the
    API response*/
	private int codigo;
	private boolean exibirDownload;
	private String nomeArquivo;
	private String titulo;
	private String url;

	public Content(int id, boolean showDownload, String fileName, String title, String url) {
		this.codigo = id;
		this.exibirDownload = showDownload;
		this.nomeArquivo = fileName;
		this.titulo = title;
		this.url = url;
	}

	public Content() {
		nomeArquivo = " ";
		url = " ";
		titulo = " ";
	}

    //TODO: Javadoc para métodos

	public int getId() {
		return this.codigo;
	}


	public boolean getShowDownload() {
		return this.exibirDownload;
	}


	public String getFileName() {
		return this.nomeArquivo;
	}


	public String getTitle() {
		return this.titulo;
	}

	public String getUrl() {
		return this.url;
	}

}