/**
 * 
 */
package com.math.service;

import java.util.List;
import java.util.Set;

import com.math.entity.Category;
import com.math.entity.MathQuery;

/**
 * @author Ajay J
 *
 * 
 */
public interface IMathQuestionBankService {

	List<MathQuery> fetchAllQuestions();

	List<Category> fetchAllCategory();

	List<MathQuery> fetchQuestionsByCategory(int categoryId);

	MathQuery saveQuestion(String question, int CategoryId, String CategoryName, String Keyword);
	
	Category saveCategory(String categoryName);

	/**
	 * @param id
	 * @param keyword
	 */
	void updateKeywordByQuesId(int id, String keyword);

	/**
	 * @param id
	 * @param keyword
	 */
	Set<MathQuery> fetchQuestionsByKeyowrds(String[] keywords);
}
