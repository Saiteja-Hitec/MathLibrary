package com.math.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.math.entity.Category;
import com.math.entity.MathQuery;

@Repository
public class MathQueryRepoImpl {

	@Autowired
	private ICategoryRepo categoryRepo;
	
	@Autowired
	private IMathQueryRepo mathqueryRepo;
	
	public List<MathQuery> fetchQueries() {
		try {
			List<MathQuery> queryList = new ArrayList<MathQuery>();
			mathqueryRepo.findAll().forEach(queryList::add);
			return queryList;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Category> fetchAllCategories() {
		try {
			List<Category> categoryList = new ArrayList<Category>();
			categoryRepo.findAll().forEach(categoryList::add);
			return categoryList;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public Category getCategoryById(int ctgId) {
		try {
			return categoryRepo.findById(ctgId).get();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public String saveQuery(MathQuery mathQuery) {
		try {
			mathqueryRepo.save(mathQuery);
			return "Success";
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public String saveCategory(Category ctg) {
		try {
			categoryRepo.save(ctg);
			return "Success";
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public List<MathQuery> getMathQueriesByCtgId(int ctgId) {
		try {
			List<MathQuery> queries = new ArrayList<MathQuery>();
	        mathqueryRepo.findByCategoryId(ctgId).forEach(queries::add);;
	        return queries;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	 
	
}
