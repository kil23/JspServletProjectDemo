package com.myapp.dao.model;

public class Sitter extends Member {

	private int yrExp;
	private int epay;
	private int memberId;
	
	public Sitter(int id, String fname, String lname, int phone, String email, String addr, String password,
			Type type, int epay, int yrExp) {
		super(fname, lname, phone, email, addr, password, type);
		memberId = id;
		this.epay = epay;
		this.yrExp = yrExp;
		
	}

	public Sitter(String fname, String lname, int phone, String email, String addr, String password, Type type, int epay, int yrExp) {
		super(fname, lname, phone, email, addr, password, type);
		this.epay = epay;
		this.yrExp = yrExp;
	}

	public Sitter() {
		super();
	}
	
	public int getYrExp() {
		return yrExp;
	}
	public void setYrExp(int yrExp) {
		this.yrExp = yrExp;
	}
	public int getEpay() {
		return epay;
	}
	public void setEpay(int epay) {
		this.epay = epay;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
}
