package com.myapp.dao.daolayer;

import com.myapp.dao.model.Sitter;

public interface SitterDaoInterf {
	
	Sitter getSitter(int id);
	boolean insertSitter(Sitter st);
	boolean updateSitter(Sitter st);
	boolean deleteSitter(int id);
}
