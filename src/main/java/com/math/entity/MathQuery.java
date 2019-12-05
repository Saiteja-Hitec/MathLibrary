package com.math.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class MathQuery {

	@Id
	@GeneratedValue
	private int queryId;
	
	private String queryDesc;

	@ManyToOne
	private Category category;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Keywords> keyWords = new HashSet<>();


	public Set<Keywords> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(Set<Keywords> keyWords) {
		this.keyWords = keyWords;
	}

	public int getQueryId() {
		return queryId;
	}

	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}

	public String getQueryDesc() {
		return queryDesc;
	}

	public void setQueryDesc(String queryDesc) {
		this.queryDesc = queryDesc;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	


}
