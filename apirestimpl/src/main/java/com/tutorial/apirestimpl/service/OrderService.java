package com.tutorial.apirestimpl.service;

import java.util.List;

import com.tutorial.apirestimpl.dto.OrderRequest;
import com.tutorial.apirestimpl.dto.OrderResponse;

public interface OrderService {
	
	OrderResponse createOrder(OrderRequest orderRequest);
	
	List<OrderResponse> getOrders();

}
