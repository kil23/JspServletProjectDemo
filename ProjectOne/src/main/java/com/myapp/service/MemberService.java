package com.myapp.service;

import com.myapp.app.form.Registerform;
import com.myapp.dao.daolayer.MemberDao;
import com.myapp.dao.daolayer.SeekerDao;
import com.myapp.dao.daolayer.SitterDao;
import com.myapp.dao.model.Member;
import com.myapp.dao.model.Member.Status;
import com.myapp.dao.model.Member.Type;
import com.myapp.dao.model.Seeker;
import com.myapp.dao.model.Sitter;
import com.myapp.util.HashUtil;

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
		
		Type type = checkType(member);
		if(type == Type.Seeker) {
			Sitter sitter = new Sitter();
			sitter.setYrExp(Integer.valueOf(reg.getYrExp()));
			sitter.setEpay(Integer.valueOf(reg.getExpectedPay()));
			sitter.setStatus(member.getStatus());
			SitterDao sitterDao = new SitterDao();
			int sitterId = sitterDao.insertSitter(sitter);
			sitter.setMemberId(sitterId);
		}
		else {
			Seeker seeker = new Seeker();
			seeker.setTotalChildren(Integer.valueOf(reg.getNoChild()));
			seeker.setSpouse(reg.getSpouse());
			seeker.setStatus(member.getStatus());
			SeekerDao seekerDao = new SeekerDao();
			int seekerId = seekerDao.insertSeeker(seeker);
			seeker.setMemberId(seekerId);
		}
		
		MemberDao memberDao = new MemberDao();
		int memberId = memberDao.insertMember(member);
		member.setId(memberId);
		return member;
	}
	
	public static Type checkType(Member mem) {
		Type type = mem.getType();
		return type;
	}
	
	
	
	
	
}
