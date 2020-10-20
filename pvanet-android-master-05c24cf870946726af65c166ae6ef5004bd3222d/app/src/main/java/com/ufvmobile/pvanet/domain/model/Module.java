package com.ufvmobile.pvanet.domain.model;

import java.util.ArrayList;

public class Module implements Comparable {

	/*the reason why the variables names are in Portuguese it's because we need them to matches the
    API response*/
	private int codigoDisciplina;
	private int codigoModulo;
	private int ordenacao;
	private String tituloModulo;
	private ArrayList<Tool> mModuleContentList;

	public Module(ArrayList<Tool> moduleContentList, String title, int id, int courseId, int order) {
		this.mModuleContentList = moduleContentList;
		this.tituloModulo = title;
		this.codigoModulo = id;
		this.codigoDisciplina = courseId;
		this.ordenacao = order;
	}

    public Module() {
        mModuleContentList = new ArrayList<>();
    }

    //TODO: Javadoc para métodos

	public int getCourseId() {
		return this.codigoDisciplina;
	}

	public int getId() {
		return this.codigoModulo;
	}

	public int getOrder() {
		return this.ordenacao;
	}

	public String getTitle() {
		return this.tituloModulo;
	}

	public ArrayList<Tool> getModuleContentList() {
		return mModuleContentList;
	}

	public void setModuleContentList(ArrayList<Tool> moduleContentList) {
		this.mModuleContentList = moduleContentList;
	}

	@Override
	public int compareTo(Object another) {
		Module module = (Module) another;
		return this.getOrder() - module.getOrder();
	}
}