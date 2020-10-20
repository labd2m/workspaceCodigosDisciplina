package com.ufvmobile.pvanet.domain.model;

/**
 * Created by igorvd on 3/22/16.
 */

public class NavDrawerItem {
    private boolean mShowNotify;
    private String mTitle;


    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title) {
        this.mShowNotify = showNotify;
        this.mTitle = title;
    }

    public boolean isShowNotify() {
        return mShowNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.mShowNotify = showNotify;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
}
