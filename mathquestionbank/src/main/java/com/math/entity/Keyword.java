/**
 * 
 */
package com.math.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author Ajay J
 *
 * 
 */

@Entity
public class Keyword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int keywordId;

	@Column
	private String keyword;

	@ManyToMany(mappedBy = "keywords", fetch = FetchType.LAZY)
	private Set<MathQuery> Question;

	public int getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(int keywordId) {
		this.keywordId = keywordId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "Keyword [keywordId=" + keywordId + ", keyword=" + keyword + "]";
	}
}
