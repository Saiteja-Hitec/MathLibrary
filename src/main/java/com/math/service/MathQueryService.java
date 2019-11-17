package com.math.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.math.entity.Category;
import com.math.entity.MathQuery;
import com.math.repository.MathQueryRepoImpl;

@Service
public class MathQueryService {
	
	@Autowired
	private MathQueryRepoImpl mathQueryRepo;
	
	public List<MathQuery> getAllQueries() {
		return mathQueryRepo.fetchQueries();
	}
	
	public List<Category> getAllCategories() {
		return mathQueryRepo.fetchAllCategories();
	}
	
	public Category getCategoryById(int ctgId) {
		return mathQueryRepo.getCategoryById(ctgId);
	}
	
	public String saveMathQuery(MathQuery mquery) {
		return mathQueryRepo.saveQuery(mquery);
	}
	
	public String saveCategory(Category ctg) {
		return mathQueryRepo.saveCategory(ctg);
	}
	
	public List<MathQuery> getQueriesByCtgId(int ctgId) {
		return mathQueryRepo.getMathQueriesByCtgId(ctgId);
	}

}
