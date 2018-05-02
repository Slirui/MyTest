package com.entity;
// Generated 2017-6-14 14:48:24 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * AnotherYear generated by hbm2java
 */
@Entity
@Table(name = "CimissCount")
public class CimissCount implements java.io.Serializable {

	private Integer id;
	private Integer count;
	private Date dateDate;
	private boolean isActive;

	public CimissCount() {
	}

	public CimissCount(Integer count, Date dateDate, boolean isActive) {
		this.count = count;
		this.dateDate = dateDate;
		this.isActive = isActive;
	}
	
	public CimissCount(Integer id, Integer count, Date dateDate, boolean isActive) {
		this.id = id;
		this.count = count;
		this.dateDate = dateDate;
		this.isActive = isActive;
	}

	@GenericGenerator(name = "generator", strategy = "identity")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Count")
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateDate", length = 23)
	public Date getDateDate() {
		return dateDate;
	}

	public void setDateDate(Date dateDate) {
		this.dateDate = dateDate;
	}

	@Column(name = "isActive")
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	

}
