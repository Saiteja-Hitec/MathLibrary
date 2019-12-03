/**
 * 
 */
package com.math.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.math.entity.Keyword;

/**
 * @author Ajay J
 *
 * 
 */
public interface KeywordRepoInterface extends JpaRepository<Keyword, Integer> {

	@Query("select keywordId from Keyword where keyword=:keyword")
	public int findIdByKeyword(@Param("keyword") String keyword);

	@Query(value = "select question_id from question_keyword where keyword_id=:keywordId", nativeQuery = true)
	public int findQuestionIdByKeywordId(@Param("keywordId") int keywordId);

}
