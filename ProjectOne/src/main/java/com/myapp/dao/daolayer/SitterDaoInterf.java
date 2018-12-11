package com.myapp.dao.daolayer;

import com.myapp.dao.model.Sitter;

public interface SitterDaoInterf {
	
	Sitter getSitter(int id);
	int insertSitter(Sitter st);
	boolean updateSitter(Sitter st);
	//boolean deleteSitter(int id);
}
