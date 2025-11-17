package com.tutorial.apirestimpl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.apirestimpl.dao.OrderDAO;
import com.tutorial.apirestimpl.dto.BookDTO;
import com.tutorial.apirestimpl.dto.OrderRequest;
import com.tutorial.apirestimpl.dto.OrderResponse;
import com.tutorial.apirestimpl.models.Order;
import com.tutorial.apirestimpl.models.OrderStatus;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private BookServiceClient bookServiceClient;

	@Override
	public OrderResponse createOrder(OrderRequest orderRequest) {
		
		// Recherche du livre par titre :
		
		BookDTO book = bookServiceClient.findBookByTitle(orderRequest.getBookTitle()).orElse(null);
		
		Order order = new Order();
		order.setBookTitle(orderRequest.getBookTitle());
		order.setQuantity(orderRequest.getQuantity());
		
		if (book == null) {
			order.setStatus(OrderStatus.BOOK_NOT_FOUND);
			order = orderDAO.save(order);
			return toResponse(order);
		}
		
		if(!book.isAvailable()) {
			order.setStatus(OrderStatus.BOOK_NOT_AVAILABLE);
			order= orderDAO.save(order);
			return toResponse(order);
		}
		
		order.setStatus(OrderStatus.CREATED);
		order = orderDAO.save(order);
		return toResponse(order);
		
	}
	
	private OrderResponse toResponse(Order order) {
		return new OrderResponse(order.getBookTitle(),order.getQuantity(),order.getStatus());
	}

	@Override
	public List<OrderResponse> getOrders() {
		List<Order> orders = orderDAO.findAll();

		return orders.stream().map( order -> toResponse(order)).toList();
		
	}

}
