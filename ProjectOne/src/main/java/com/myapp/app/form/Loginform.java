package com.myapp.app.form;

import java.util.HashMap;

import com.myapp.service.MemberService;
import com.myapp.util.HashUtil;

public class Loginform {
	
	private String email;
	private String password;
	
	public Loginform(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	HashMap<String, String> errorsLogin = new HashMap<>();
	
	public HashMap<String, String> validate() {
		
		if(password == null || password.trim()=="") {
			errorsLogin.put("passwordError", "Password cannot be empty.");
		}
		
		if(email == null || email.trim()=="") {
			errorsLogin.put("emailError", "Email cannot be empty.");
		}else if(email.matches("")){
			errorsLogin.put("emailError", "Wrong email!! Enter proper Email address.");
		}
		
		if(!(MemberService.existingUserCheck(email, hashedPassword)==null)) // check whether the email is already registered or not.
		{
			errorsLogin.put("loginError", "Wrong Email/Password... Try again.");
		}
		return errorsLogin;
	}
	
	public static final String SALT = "my-salt-text";
	String saltedPassword = SALT + password;
	String hashedPassword = HashUtil.generateHash(saltedPassword);
}
