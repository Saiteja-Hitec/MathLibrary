/**
 * 
 */
package com.math.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.math.entity.MathQuery;

/**
 * @author Ajay J
 *
 * 
 */
public interface MathQueryRepoInterface extends JpaRepository<MathQuery, Integer> {

	@Query(value = "select * from math_query", nativeQuery = true)
	List<MathQuery> getAllQuestions();

	@Query(value = "select * from math_query where category_id = :categoryId ", nativeQuery = true)
	List<MathQuery> getCategoryQuestions(@Param("categoryId") int categoryId);

	

}
