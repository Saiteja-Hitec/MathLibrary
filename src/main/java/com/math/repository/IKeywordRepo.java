package com.math.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.math.entity.Keywords;

public interface IKeywordRepo extends JpaRepository<Keywords, Integer> {

}
