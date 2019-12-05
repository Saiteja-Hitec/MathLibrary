package com.math.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.math.entity.Category;
import com.math.entity.Keywords;
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
	
	public String saveMathQuery(MathQuery mquery, String keyword) {
		Set<Keywords> keywordList = new HashSet<Keywords>();
		if(keyword != "NA") {
			String[] keywords = keyword.trim().split(",");
			for (int i = 0; i < keywords.length; i++) {
				Keywords word = new Keywords();
				word.setKeywordIdf(keywords[i].trim().toLowerCase());
				keywordList.add(word);
			}
			System.out.println(keywordList.size());
			mquery.setKeyWords(keywordList);
		}
		return mathQueryRepo.saveQuery(mquery);
	}
	
	public String saveCategory(Category ctg) {
		return mathQueryRepo.saveCategory(ctg);
	}
	
	public void saveKeywordsToQuery(int queryId, String keyword) {
		if(keyword != "NA") {
			String[] keywords = keyword.trim().split(",");
			MathQuery query = mathQueryRepo.getMathQueryById(queryId);
			Set<Keywords> KeyListSet = query.getKeyWords();
			for (int i = 0; i < keywords.length; i++) {
				for(Keywords word: KeyListSet) {
					if(word.getKeywordIdf().toLowerCase() == keywords[i].trim().toLowerCase())
					{
						
						Keywords kword = new Keywords();
						word.setKeywordIdf(keywords[i].trim().toLowerCase());
					    KeyListSet.add(kword);
					    
					}
				}
			}
			query.setKeyWords(KeyListSet);
		    mathQueryRepo.saveQuery(query);
		}
	}
	
	public Set<MathQuery> getQueriesBySearch(String[] keywords) {
		Set<MathQuery> filteredList = new HashSet<MathQuery>();
		this.getAllQueries().stream().forEach(query -> {
			for(String word: keywords) {
				query.getKeyWords().stream().forEach(keywrd -> {
					if(word.trim().equalsIgnoreCase(keywrd.getKeywordIdf())) {
						filteredList.add(query);
					}
				});
			}
		});
		return filteredList;
	}
	
	public List<MathQuery> getQueriesByCtgId(int ctgId) {
		return mathQueryRepo.getMathQueriesByCtgId(ctgId);
	}

}
