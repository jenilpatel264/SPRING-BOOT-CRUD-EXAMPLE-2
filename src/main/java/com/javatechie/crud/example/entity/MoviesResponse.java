package com.javatechie.crud.example.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;



public class MoviesResponse {
	
	
	
	@JsonProperty("page")
	private int page;
	
	
	@Override
	public String toString() {
		return "MoviesResponse [page=" + page + ", results=" + results + ", getPage()=" + getPage() + ", getResults()="
				+ getResults() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	@JsonProperty("results")
	private List<results> results;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<results> getResults() {
		return results;
	}

	public void setResults(List<results> results) {
		this.results = results;
	}
	
	

}
