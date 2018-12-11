package com.myapp.dao.daolayer;

import com.myapp.dao.model.Seeker;

public interface SeekerDaoInterf {

	Seeker getSeeker(int id);
	int insertSeeker(Seeker sk);
	boolean updateSeeker(Seeker sk);
	//boolean deleteSeeker(int id);
}
