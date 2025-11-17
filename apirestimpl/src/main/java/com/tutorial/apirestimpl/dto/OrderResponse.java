package com.tutorial.apirestimpl.dto;

import com.tutorial.apirestimpl.models.OrderStatus;

public class OrderResponse {
	
	private String bookTitle;
	
	private Integer quantity;
	
	private OrderStatus status;
	
	public OrderResponse() {}
	
	public OrderResponse(String bookTitle, Integer quantity, OrderStatus status) {
		this.bookTitle=bookTitle;
		this.quantity=quantity;
		this.status=status;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	
}
