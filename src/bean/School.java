package bean;

import java.io.Serializable;

public class School implements Serializable{
	private String cd;
	private String name;
	public String getCd(){
		return cd;
	}
	public void setCd(String cd){
		this.cd = cd;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
}