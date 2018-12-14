package com.myapp.app.form;

import java.util.HashMap;

import com.myapp.dao.model.Member;
import com.myapp.dao.model.Member.Type;
import com.myapp.service.MemberService;
import com.myapp.util.ValidationUtil;

public class Profileform {	
	
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
	private String numChild;
	private String spouseName;
	
	public Profileform(String fname, String lname, String phone, Type type, String email, String addr, String password,
			String cpassword, String yrExp, String expectedPay, String numChild, String spouseName) {
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
		this.numChild = numChild;
		this.spouseName = spouseName;
	}
	
	public Profileform() {
	}

	ValidationUtil validation = new ValidationUtil();
	
	public HashMap<String, String> Authenticate(){
		HashMap<String,String> errorsReg = new HashMap<>();
		boolean resFname = ValidationUtil.verifyFname(fname);
		if(!resFname) {
			errorsReg.put("fnameError", "First name should be alphanumeric and cannot be empty");
		}
		
		boolean reslname = ValidationUtil.verifyLname(lname);
		if(!reslname) {
			errorsReg.put("fnameError", "First name should be alphanumeric and cannot be empty");
		}
		
		boolean resphone = ValidationUtil.verifyPhone(phone);
		if(!resphone) {
			errorsReg.put("phoneError", "Phone-number should 10 digits and cannot be empty");
		}
		
		boolean resemail = ValidationUtil.verifyEmail(email);
		if(resemail) {
			if(MemberService.existingEmailCheck(email)) { 
				errorsReg.put("emailError", "Email already used.");
			}
		}
		else {
			errorsReg.put("emailError", "Wrong Email address type. Enter again");
		}
		
		boolean resaddr = ValidationUtil.verifyAddr(addr);
		if(!resaddr) {
			errorsReg.put("addrError", "Address cannot be empty.");
		}
		
		boolean respassword = ValidationUtil.verifyPassword(password);
		if(!respassword) {
			errorsReg.put("passwordError", "Password cannot be empty.");
		}
		
		boolean rescpassword = ValidationUtil.verifyCpassword(cpassword);
		if(!rescpassword) {
			errorsReg.put("cpasswordError", "Confirm password cannot be empty.");
		}
		
		boolean compare = ValidationUtil.checkPassword(password, cpassword);
		if(!compare) {
			errorsReg.put("passwordcompareError", "Passwords doesnot match. Enter again!!");
		}
		
		if(type == Member.Type.Seeker) {
			
			boolean resNumChildren = ValidationUtil.verifyNumChild(numChild);
			if(!resNumChildren) {
				errorsReg.put("numChildrenError", "Number of children only contains numbers and cannot be empty.");
			}
			
			boolean resSpouseName = ValidationUtil.verifySpouseName(spouseName);
			if(!resSpouseName) {
				errorsReg.put("spouseNameError", "Spouse name should have letters only and cannot be empty.");
			}
		}
		else {
			boolean resYrExp = ValidationUtil.verifyYearExp(yrExp);
			if(!resYrExp) {
				errorsReg.put("yrExpError", "Experience cannot be empty and should contain number only.");
			}
			
			boolean resPay = ValidationUtil.verifyExpectedPay(expectedPay);
			if(!resPay) {
				errorsReg.put("payError", "Expected pay should be numeric only and cannot be empty.");
			}
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
	public String getNumChild() {
		return numChild;
	}
	public void setNumChild(String numChild) {
		this.numChild = numChild;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}	
	
}
