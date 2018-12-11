package com.myapp.dao.model;

public class Seeker extends Member{

	private int totalChildren;
	private String spouseName;
	private int memberId;
	
	
	public Seeker(int id, String fname, String lname, int phone, String email, String addr, String password,
			Type type, int totalChildren, String spouseName) {
		super(fname, lname, phone, email, addr, password, type);
		memberId = id;
		this.totalChildren = totalChildren;
		this.spouseName = spouseName;
		
	}
	public Seeker(String fname, String lname, int phone, String email, String addr, String password, Type type,  int totalChildren, String spouseName) {
		super(fname, lname, phone, email, addr, password, type);
		this.totalChildren = totalChildren;
		this.spouseName = spouseName;
	}
	
	public Seeker() { }
	
	public int getTotalChildren() {
		return totalChildren;
	}
	public void setTotalChildren(int totalChildren) {
		this.totalChildren = totalChildren;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouse(String spouseName) {
		this.spouseName = spouseName;
	}
	
}
