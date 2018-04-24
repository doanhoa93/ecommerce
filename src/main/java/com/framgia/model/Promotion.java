package com.framgia.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Promotion implements Serializable {
	private Integer id;
	private Date startDate;
	private Date endDate;
	private float saleOf;

	public Promotion() {
	}

	public Promotion(Integer id) {
		this.id = id;
	}

	public Promotion(Integer id, Date startDate, Date endDate) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getSaleOf() {
		return saleOf;
	}

	public void setSaleOf(float saleOf) {
		this.saleOf = saleOf;
	}
}
