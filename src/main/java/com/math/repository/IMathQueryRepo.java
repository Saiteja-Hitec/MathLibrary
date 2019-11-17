package com.math.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.math.entity.MathQuery;

public interface IMathQueryRepo extends JpaRepository<MathQuery, Integer> {

	@Query(value="SELECT * FROM MATH_QUERY WHERE CATEGORY_CATEGORY_ID = ?1", nativeQuery = true)
	List<MathQuery> findByCategoryId(int categoryId);
}
