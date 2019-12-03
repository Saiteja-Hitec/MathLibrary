package com.math.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class MathQuery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;

	@Column
	private String questionDesc;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private Category category;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "question_keyword", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "keyword_id"))
	private Set<Keyword> keywords = new HashSet<>();

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "MathQuery [questionId=" + questionId + ", questionDesc=" + questionDesc + ", category=" + category
				+ ", keywords=" + keywords + "]";
	}

}
