package com.math.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Keywords {

	@Id
	@GeneratedValue
	private int keyId;
	
	private String keywordIdf;
	
	
	public int getKeyId() {
		return keyId;
	}

	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}

	public String getKeywordIdf() {
		return keywordIdf;
	}

	public void setKeywordIdf(String keywordIdf) {
		this.keywordIdf = keywordIdf;
	}
	
}
