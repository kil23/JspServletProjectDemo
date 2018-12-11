package com.myapp.app.form;

import java.util.HashMap;

import com.myapp.dao.model.Member;
import com.myapp.dao.model.Member.Type;
import com.myapp.service.MemberService;

public class Registerform {	
	
	private String fname ;
	private String lname ;
	private String phone ;
	private Type type ;
	private String email ;
	private String addr ;
	private String password ;
	private String cpassword ;
	private String yrExp;
	private String expectedPay;
	private String noChild;
	private String spouse;
	
	public Registerform(String fname, String lname, String phone, Type type, String email, String addr, String password,
			String cpassword, String yrExp, String expectedPay, String noChild, String spouse) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.type = type;
		this.email = email;
		this.addr = addr;
		this.password = password;
		this.cpassword = cpassword;
		this.yrExp = yrExp;
		this.expectedPay = expectedPay;
		this.noChild = noChild;
		this.spouse = spouse;
	}

	private HashMap<String,String> errorsReg = new HashMap<>();
	
	Member mem = new Member();
	
	public HashMap<String,String> validate() {
		if(fname == null || fname.trim()=="") {
			errorsReg.put("fnameError", "First_Name cannot be empty.");
		}else if(fname.matches("")){
			errorsReg.put("fnameError", "First_Name should have letters only.");
		}
		
		if(lname == null || lname.trim()=="") {
			errorsReg.put("lnameError", "Last_Name cannot be empty.");
		}else if(lname.matches("")){
			errorsReg.put("lnameError", "Last_Name should have letters only.");
		}
		
		if(phone == null || phone.trim()=="") {
			errorsReg.put("phoneError", "Phone-number cannot be empty.");
		}else if(!phone.matches("^[0-9]{10}$")){
			errorsReg.put("phoneError", "Phone-number should 10 digits.");
		}
		
		if(email == null || email.trim()=="") {
			errorsReg.put("emailError", "Email cannot be empty.");
		}else if(email.matches("")){
			errorsReg.put("emailError", "Wrong email!! Enter proper Email address.");
		}else if(MemberService.existingEmailCheck(email)) { // check whether the email is already registered or not.
			errorsReg.put("emailError", "Email address already used.");
		}
		
		if(addr == null || addr.trim()=="") {
			errorsReg.put("addrError", "Address cannot be empty.");
		}
		
		if(password == null || password.trim()=="") {
			errorsReg.put("passwordError", "Password cannot be empty.");
		}
		if(cpassword == null || cpassword.trim()=="") {
			errorsReg.put("cpasswordError", "Confirm password cannot be empty.");
		}
		if(!password.equals(cpassword)) {
			errorsReg.put("passwordcompareError", "Passwords doesnot match. Enter again!!");
		}
		return errorsReg;
	}

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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public String getYrExp() {
		return yrExp;
	}
	public void setYrExp(String yrExp) {
		this.yrExp = yrExp;
	}
	public String getExpectedPay() {
		return expectedPay;
	}
	public void setExpectedPay(String expectedPay) {
		this.expectedPay = expectedPay;
	}
	public String getNoChild() {
		return noChild;
	}
	public void setNoChild(String noChild) {
		this.noChild = noChild;
	}
	public String getSpouse() {
		return spouse;
	}
	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	
	
//	public boolean yearExpVerify(String yrExp) {
//	String regexStr = "^[0-9]{2}$";
//	
//	if(yrExp.matches(regexStr))
//	{
//		return true;
//	}
//	return false;
//}
//
//public boolean expectedPayVerify(String pay) {
//	String regexStr = "^[0-9]{2}$";
//	
//	if(pay.matches(regexStr))
//	{
//		return true;
//	}
//	return false;
//}
}
