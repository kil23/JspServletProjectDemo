package com.myapp.dao.model;

public class Member {

	private String fname;
	private String lname;
	private int phone;
	private String email;
	private String addr;
	private String password;
	private int id;	
	private Type type;
	private Status status;

	public enum Type{ Seeker , Sitter}
	
	public enum Status{ Active, Inactive}
	
	public Member(int id, String fname, String lname, int phone, String email, String addr, String password, Type type, Status status) {
		
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.email = email;
		this.addr = addr;
		this.password = password;
		this.type = type;	
		this.status = status;
	}
	
public Member(int id, String fname, String lname, int phone, String email, String addr, String password, Type type) {
		
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.email = email;
		this.addr = addr;
		this.password = password;
		this.type = type;	
	}
	
	public Member( String fname, String lname, int phone, String email, String addr, String password, Type type) {
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.email = email;
		this.addr = addr;
		this.password = password;
		this.type = type;
	}
	
	public Member() { }
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
