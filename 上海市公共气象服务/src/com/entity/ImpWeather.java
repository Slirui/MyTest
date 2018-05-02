package com.entity;
// Generated 2017-6-23 14:46:29 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ImpWeather generated by hbm2java
 */
@Entity
@Table(name = "ImpWeather")
public class ImpWeather implements java.io.Serializable {
	private int id;
	private String title;
	private String erJi;
	private String sanJi;
	private String contents;

	public ImpWeather() {
	}

	public ImpWeather(int id) {
		this.id = id;
	}

	public ImpWeather(int id, String title, String contents) {
		this.id = id;
		this.title = title;
		this.contents = contents;
	}

	@GenericGenerator(name = "generator", strategy = "identity")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "erJi")
	public String getErJi() {
		return erJi;
	}

	public void setErJi(String erJi) {
		this.erJi = erJi;
	}

	@Column(name = "Sanji")
	public String getSanJi() {
		return sanJi;
	}

	public void setSanJi(String sanJi) {
		this.sanJi = sanJi;
	}

	@Column(name = "Contents")
	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
