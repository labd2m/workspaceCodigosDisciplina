package com.ufvmobile.pvanet.domain.model;

import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Represent a single Topic from a {@link Tool}
 */
public class Topic {

	/*the reason why the variables names are in Portuguese it's because we need them to matches the
    API response*/
	private int codigo;
	private String descricao;
	ArrayList<Content> mContentList;

	public Topic(int id, String description) {
		this.codigo = id;
		this.descricao = description;
	}

    public Topic(){
        mContentList = new ArrayList<>();
    }

    //TODO: Javadoc para métodos

	public int getId() {
		return this.codigo;
	}

	public String getDescription() {
		return this.descricao;
	}

	public ArrayList<Content> getContentList() {
		return mContentList;
	}

	public void setContentList(@Nullable ArrayList<Content> contentList) {
		if(contentList == null) return;
		mContentList = contentList;
	}
}