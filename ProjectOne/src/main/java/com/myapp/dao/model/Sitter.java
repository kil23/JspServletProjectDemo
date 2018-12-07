package com.myapp.dao.model;

public class Sitter extends Member {

	private String yrExp;
	private String epay;
	public Sitter(String fname, String lname, int phone, String email,String yrExp, String epay, String addr, 
			String password, int memberId, Type type, Status status) {
		super(fname, lname, phone, email, addr, password, memberId, type, status);
		this.yrExp = yrExp;
		this.epay = epay;
	}
	
	public Sitter() {
		super();
	}
	
	public String getYrExp() {
		return yrExp;
	}
	public void setYrExp(String yrExp) {
		this.yrExp = yrExp;
	}
	public String getEpay() {
		return epay;
	}
	public void setEpay(String epay) {
		this.epay = epay;
	}

}
