/**
 * 
 */
package com.math.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.math.entity.Category;
import com.math.entity.Keyword;
import com.math.entity.MathQuery;
import com.math.repository.CategoryRepoInterface;
import com.math.repository.KeywordRepoInterface;
import com.math.repository.MathQueryRepoInterface;
import com.math.service.IMathQuestionBankService;

/**
 * @author Ajay J
 *
 * 
 */
@Service
@Transactional
public class MathQuestionBankSeriveImpl implements IMathQuestionBankService {

	@Autowired
	private MathQueryRepoInterface mathqueryDao;

	@Autowired
	private CategoryRepoInterface categortDao;

	@Autowired
	private KeywordRepoInterface keywordDao;

	public List<Category> fetchAllCategory() {
		return this.categortDao.getAllCategories();

	}

	public List<MathQuery> fetchAllQuestions() {
		return this.mathqueryDao.getAllQuestions();
	}

	@Override
	public List<MathQuery> fetchQuestionsByCategory(int categoryId) {

		return this.mathqueryDao.getCategoryQuestions(categoryId);
	}

	@Override
	public MathQuery saveQuestion(String question, int categoryId, String categoryName, String keyword) {

		Category category = new Category();
		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName);

		MathQuery query = new MathQuery();
		query.setQuestionDesc(question);
		query.setCategory(category);

		if (keyword != null && keyword != "") {
			Keyword keywords = new Keyword();
			keywords.setKeyword(keyword);

			this.keywordDao.save(keywords);
			query.getKeywords().add(keywords);
		}

		return this.mathqueryDao.save(query);
	}

	@Override
	public Category saveCategory(String categoryName) {
		Category category = new Category();
		category.setCategoryName(categoryName.toLowerCase());
		return this.categortDao.save(category);
	}

	@Override
	public void updateKeywordByQuesId(int id, String keyword) {

		Keyword keywords = new Keyword();
		keywords.setKeyword(keyword);

		this.keywordDao.save(keywords);

		MathQuery query = this.mathqueryDao.getOne(id);

		query.getKeywords().add(keywords);
		this.mathqueryDao.save(query);

	}

	@Override
	public Set<MathQuery> fetchQuestionsByKeyowrds(String[] keywords) {
		Set<MathQuery> list = new HashSet<MathQuery>();
		this.fetchAllQuestions().stream().forEach(problems -> {
			for (String keys : keywords) {
				problems.getKeywords().stream().forEach(keyword -> {
					if (keyword.getKeyword().equals(keys)) {
						System.out.println("PRINTING--->" + problems.getQuestionDesc());
						list.add(problems);
					}
				});
			}
		});

		return list;
	}

}
