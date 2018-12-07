package com.myapp.dao.model;

public class Seeker extends Member{

	private String noOfChildren;
	private String spouse;
	public Seeker(String fname, String lname, int phone, String email,
			String noOfChildren, String spouse, String addr, String password, int memberId, Type type, Status status) {
		
		super(fname, lname, phone, email,  addr, password, memberId, type, status);
		this.noOfChildren = noOfChildren;
		this.spouse = spouse;
	}
	
	public Seeker() {
		super();
	}
	
	public String getNoOfChildren() {
		return noOfChildren;
	}
	public void setNoOfChildren(String noOfChildren) {
		this.noOfChildren = noOfChildren;
	}
	public String getSpouse() {
		return spouse;
	}
	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}
	
}
