package bean;

import java.io.Serializable;

public class Test implements Serializable{
	private String student_no;
	private String subject_cd;
	private String school_cd;
	private int no;
	private int point;
	private String class_num;


	public String getStudnet_NO(){
		return student_no;
	}
	public void setStudent_NO(String student_no){
		this.student_no = student_no;
	}
	public String getSubject_CD(){
		return subject_cd;
	}
	public void setSubject_CD(String subject_cd){
		this.subject_cd = subject_cd;
	}
	public String getSchool_CD(){
		return school_cd;
	}
	public void setSchool_CD(String school_cd){
		this.school_cd = school_cd;
	}
	public int getNo(){
		return no;
	}
	public void setNo(int no){
		this.no = no;
	}
	public int getPoint(){
		return point;
	}
	public void setPoint(int point){
		this.point = point;
	}
	public String getClass_Num(){
		return class_num;
	}
	public void setClass_Num(String class_num){
		this.class_num = class_num;
	}
}