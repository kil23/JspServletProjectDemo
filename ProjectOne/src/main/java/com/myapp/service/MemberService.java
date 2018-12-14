package com.myapp.service;

import java.util.List;

import com.myapp.app.form.Profileform;
import com.myapp.dao.daolayer.JobAppDao;
import com.myapp.dao.daolayer.JobDao;
import com.myapp.dao.daolayer.MemberDao;
import com.myapp.dao.daolayer.SeekerDao;
import com.myapp.dao.daolayer.SitterDao;
import com.myapp.dao.model.Job;
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
	
	public static Member insertNewMember(Profileform reg) {
		Member member = memberPopulate(reg);
		MemberDao memberDao = new MemberDao();
		int memberId = memberDao.insertMember(member);
		member.setId(memberId);
		return member;
	}
	
	public static Sitter insertNewSitter(int memberid, Profileform pf) {
		Sitter sitter = new Sitter();
		sitter.setMemberId(memberid);
		sitter.setYrExp(Integer.valueOf(pf.getYrExp()));
		sitter.setEpay(Integer.valueOf(pf.getExpectedPay()));
		SitterDao sitterDao = new SitterDao();
		sitterDao.insertSitter(sitter);
		return sitter;
	}
	
	public static Seeker insertNewSeeker(int memberid, Profileform pf) {
		Seeker seeker = new Seeker();
		seeker.setTotalChildren(Integer.valueOf(pf.getNumChild()));
		seeker.setSpouse(pf.getSpouseName());
		SeekerDao seekerDao = new SeekerDao();
		seekerDao.insertSeeker(seeker);
		return seeker;
	}
	public static Type checkType(Member mem) {
		Type type = mem.getType();
		return type;
	}
	
	public static Member getMemberById(int id) {
		MemberDao dao = new MemberDao();
		Member member = dao.getMember(id);
		return member;
	}
	
	public static boolean updateSitterInfo(Profileform pf) {
		Member member = memberPopulate(pf);
		MemberDao memberDao = new MemberDao();
		boolean memResult = memberDao.updateMember(member);
		Sitter sitter = new Sitter();
		sitter.setMemberId(member.getId());
		sitter.setYrExp(Integer.valueOf(pf.getYrExp()));
		sitter.setEpay(Integer.valueOf(pf.getExpectedPay()));
		SitterDao sitterDao = new SitterDao();
		boolean sitterResult = sitterDao.updateSitter(sitter);
		if(memResult && sitterResult) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean updateSeekerInfo(Profileform reg) {
		Member member = memberPopulate(reg);
		MemberDao memberDao = new MemberDao();
		boolean memResult = memberDao.updateMember(member);
		Seeker seeker = new Seeker();
		seeker.setMemberId(member.getId());
		seeker.setTotalChildren(Integer.valueOf(reg.getNumChild()));
		seeker.setSpouse(reg.getSpouseName());
		SeekerDao seekerDao = new SeekerDao();
		boolean seekerResult = seekerDao.updateSeeker(seeker);
		if(memResult && seekerResult) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean deleteUser(int userid) {
		MemberDao memberDao = new MemberDao();
		JobAppDao jobAppDao = new JobAppDao();
		JobDao jobDao = new JobDao();
		
		Type type = memberDao.getMember(userid).getType();
		
		if(type == Member.Type.Seeker) {
			List<Job> jobsList = jobDao.getUserJobs(userid);
			for(Job job: jobsList) {
				int numOfDeletedJobs = jobAppDao.deleteUsingJobAppId(job.getJobid());
				if(numOfDeletedJobs >= 0) {
					jobDao.deleteJob(job.getJobid());
				}
			}
		}
		else {
			jobAppDao.deleteUsingUserId(userid);
		}
		
		return memberDao.deleteMember(userid);
	}
	
	public static Member memberPopulate(Profileform pf) {
		Member member = new Member();
		member.setFname(pf.getFname());
		member.setLname(pf.getLname());
		member.setPhone(Integer.parseInt(pf.getPhone()));
		member.setEmail(pf.getEmail());
		member.setType(pf.getType());
		member.setAddr(pf.getAddr());
		member.setpassword(HashUtil.passwordHashing(pf.getPassword()));
		member.setStatus(Status.valueOf("Active"));
		return member;
	}
	
}
