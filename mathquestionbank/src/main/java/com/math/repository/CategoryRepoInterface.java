/**
 * 
 */
package com.math.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.math.entity.Category;
import com.math.entity.MathQuery;

/**
 * @author Ajay J
 *
 * 
 */
public interface CategoryRepoInterface extends JpaRepository<Category, Integer> {

	@Query(value = "select * from Category", nativeQuery = true)
	List<Category> getAllCategories();
}
