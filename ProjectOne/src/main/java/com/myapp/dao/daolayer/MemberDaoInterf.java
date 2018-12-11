package com.myapp.dao.daolayer;

import com.myapp.dao.model.Member;

public interface MemberDaoInterf {
	
	Member getMember(int id);
	boolean checkEmail(String email);
	Member checkUserCredential(String email, String password);
	int insertMember(Member mem);
	boolean updateMember(Member mem);
	//boolean deleteMember(int id);
}
