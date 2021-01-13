package edu.poly.spring.dao;

import java.util.List;


import edu.poly.spring.entity.Category;

public interface CategoryDAO {

	List<Category> danhsach(int id);

	void capnhat(Category entity);

	Category them(Category entity);

	Category findId(String id);

	List<Category> danhsach();
	
}
