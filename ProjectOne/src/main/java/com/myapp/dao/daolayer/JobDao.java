package com.myapp.dao.daolayer;

import java.sql.Connection;

import com.myapp.dao.model.Job;

public class JobDao implements JobDaoInterf{

	public boolean insertJob(Job job) {
		Connection conn = Conn.getConnection();
		return false;
	}

	public boolean updateJob(Job job) {
		Connection conn = Conn.getConnection();
		return false;
	}

	public boolean deleteJob(int jobid) {
		Connection conn = Conn.getConnection();
		return false;
	}
	
	public Job getJob(int jobAppId) {
		Connection conn = Conn.getConnection();
		return null;
	}
}
