package com.tutorial.apirestimpl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.apirestimpl.dto.OrderRequest;
import com.tutorial.apirestimpl.dto.OrderResponse;
import com.tutorial.apirestimpl.service.OrderService;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(
			@RequestBody OrderRequest orderRequest
			){
		
		OrderResponse response = orderService.createOrder(orderRequest);
		
		// Adaptation du code HTTP selon le status
		
		switch (response.getStatus()) {
			case CREATED: {
				return ResponseEntity.ok(response);
			}
			case BOOK_NOT_FOUND: {
				return ResponseEntity.status(404).body(response);
			}
			case BOOK_NOT_AVAILABLE: {
				return ResponseEntity.status(409).body(response);
			}
		
		default:
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<OrderResponse>> getOrders(){
		try {
			List<OrderResponse> response = orderService.getOrders();
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
