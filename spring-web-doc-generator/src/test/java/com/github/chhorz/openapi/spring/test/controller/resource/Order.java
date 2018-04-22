package com.github.chhorz.openapi.spring.test.controller.resource;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The order.
 *
 * @author chhorz
 *
 */
public class Order {

	/**
	 * The order number.
	 */
	private Long number;
	/**
	 * The ordered article.
	 */
	private List<Article> article;
	/*
	 * A list of reference numbers.
	 */
	private List<String> referenceNumber;
	/*
	 * The date of the order.
	 */
	private LocalDateTime orderTs;

	public Long getNumber() {
		return number;
	}

	public void setNumber(final Long number) {
		this.number = number;
	}

	public List<Article> getArticle() {
		return article;
	}

	public void setArticle(final List<Article> article) {
		this.article = article;
	}

	public List<String> getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(final List<String> referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public LocalDateTime getOrderTs() {
		return orderTs;
	}

	public void setOrderTs(final LocalDateTime orderTs) {
		this.orderTs = orderTs;
	}

}
