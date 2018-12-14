package com.myapp.util;

import java.util.HashMap;

import com.myapp.dao.model.Member;
import com.myapp.service.MemberService;

public class ValidationUtil {
	
	public static boolean verifyFname(String fname) {
		
		if(fname!=null && fname.trim().matches("^[\\p{L}]+$")){
			return true;
		}
		return false;
	}
	
	public static boolean verifyLname(String lname) {
		
		if(lname!=null && lname.trim().matches("^[\\p{L}]+$")) {
			return true;
		}
		return false;
	}
	
	public static boolean verifyPhone(String phone) {
		String regexStr = "^[0-9]{10}$";
		if(phone != null && phone.trim().matches(regexStr)) {
			return true;
		}
		return false;
	}
	
	public static boolean verifyEmail(String email) {
		String regexStr = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+.)+[a-z]{2,5}$";
		if(email != null && email.trim().matches(regexStr)) {
			return true;
		}
		return false;
	}
	
	public static boolean verifyAddr(String addr) {
		if(addr != null && addr.trim()!="") {
			return true;
		}
		return false;
	}
	
	public static boolean verifyPassword(String password) {
		if(password != null && password.trim()!="") {
			return true;
		}
		return false;
	}
	
	public static boolean verifyCpassword(String cpassword) {
		
		if(cpassword != null && cpassword.trim()!="") {
			return true;
		}
		return false;
	}
	
	public static boolean checkPassword(String password, String cpassword) {
		if(password.equals(cpassword)) {
			return true;
		}
		return false;
	}
	
	public static boolean verifyYearExp(String yrExp) {
		String regexStr = "^[0-9]{1,4}$";
		if(yrExp != null && yrExp.matches(regexStr)) {
			return true;
		}
		return false;
	}

	public static boolean verifyExpectedPay(String pay) {
		String regexStr = "^[0-9]{1,6}$";
		if(pay != null && pay.trim().matches(regexStr)) {
			return true;
		}
		return false;
	}
	
	public static boolean verifyNumChild(String numChild) {
		String regexStr = "^[0-9]{1,3}$";
		if(numChild != null && numChild.matches(regexStr)) {
			return true;
		}
		return false;
	}
	
	public static boolean verifySpouseName(String spouseName) {
		if(spouseName != null && spouseName.matches("^[\\p{L}]+$")) {
			return true;
		}
		return false;
	}
}
