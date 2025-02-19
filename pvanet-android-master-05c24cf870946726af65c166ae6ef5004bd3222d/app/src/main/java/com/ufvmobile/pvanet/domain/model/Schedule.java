package com.ufvmobile.pvanet.domain.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule {

    @SerializedName("atividades")
    @Expose
    private List<String> mAssigments = new ArrayList<String>();

    @SerializedName("agendamentos")
    @Expose
    private List<String> mAgenda = new ArrayList<String>();

    @SerializedName("disciplina")
    @Expose
    private String mCourseName;

    /**
     * No args constructor for use in serialization
     *
     */
    public Schedule() {
    }

    /**
     *
     * @param mCourseName
     * @param mSchedule
     * @param mAssigments
     */
    public Schedule(List<String> mAssigments, List<String> mSchedule, String mCourseName) {
        this.mAssigments = mAssigments;
        this.mAgenda = mSchedule;
        this.mCourseName = mCourseName;
    }

    /**
     *
     * @return
     *     The mAssigments
     */
    public List<String> getAssigments() {
        return mAssigments;
    }

    /**
     *
     * @param assigments
     *     The mAssigments
     */
    public void setAssigments(List<String> assigments) {
        this.mAssigments = assigments;
    }

    /**
     *
     * @return
     *     The mAgenda
     */
    public List<String> getAgenda() {
        return mAgenda;
    }

    /**
     *
     * @param agenda
     *     The mAgenda
     */
    public void setAgenda(List<String> agenda) {
        this.mAgenda = agenda;
    }

    /**
     *
     * @return
     *     The mCourseName
     */
    public String getCourseName() {
        return mCourseName;
    }

    /**
     *
     * @param courseName
     *     The mCourseName
     */
    public void setCourseName(String courseName) {
        this.mCourseName = courseName;
    }

}
