package com.myapp.service;

import com.myapp.app.form.Registerform;
import com.myapp.dao.daolayer.MemberDao;
import com.myapp.dao.model.Member;
import com.myapp.dao.model.Member.Status;
import com.myapp.dao.model.Member.Type;

public class MemberService {
	
	public static boolean existingEmailCheck(String email) {
		MemberDao memberDao = new MemberDao();
		boolean duplicateEmail = memberDao.checkEmail(email);
		return duplicateEmail;
	}
	
	public static Member existingUserCheck(String email, String password) {
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.checkUserCredential(email, password);
		return member;
	}
	
	public static Member insertNewUser(Registerform reg) {
		Member member = new Member();
		member.setFname(reg.getFname());
		member.setLname(reg.getLname());
		member.setPhone(Integer.parseInt(reg.getPhone()));
		member.setEmail(reg.getEmail());
		member.setType(reg.getType());
		member.setAddr(reg.getAddr());
		member.setpassword(HashUtil.passwordHashing(reg.getPassword()));
		member.setStatus(Status.valueOf("Active"));
		
		MemberDao memberDao = new MemberDao();
		int memberId = memberDao.insertMember(member);
		member.setMemberId(memberId);
		return member;
	}
	
	public static Type checkType(Member mem) {
		Type type = mem.getType();
		return type;
	}
	
	
	
}
