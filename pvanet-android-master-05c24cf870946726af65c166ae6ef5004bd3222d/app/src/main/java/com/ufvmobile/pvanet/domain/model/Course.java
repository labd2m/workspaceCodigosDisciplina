package com.ufvmobile.pvanet.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {

	/*the reason why the variables names are in Portuguese it's because we need them to matches the
    API response*/
	private int codigoDisciplina;
	private String nomeDisciplina;
	private String siglaDisciplina;

	public Course(int id, String courseName, String courseAcronym) {
		this.codigoDisciplina = id;
		this.nomeDisciplina = courseName;
		this.siglaDisciplina = courseAcronym;
	}

    //TODO: Javadoc para métodos

	public int getId() {
		return this.codigoDisciplina;
	}


	public String getCourseName() {
		return this.nomeDisciplina;
	}


	public String getCourseAcronym() {
		return this.siglaDisciplina;
	}


	protected Course(Parcel in) {
		codigoDisciplina = in.readInt();
		nomeDisciplina = in.readString();
		siglaDisciplina = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(codigoDisciplina);
		dest.writeString(nomeDisciplina);
		dest.writeString(siglaDisciplina);
	}

	@SuppressWarnings("unused")
	public static final Creator<Course> CREATOR = new Creator<Course>() {
		@Override
		public Course createFromParcel(Parcel in) {
			return new Course(in);
		}

		@Override
		public Course[] newArray(int size) {
			return new Course[size];
		}
	};
}