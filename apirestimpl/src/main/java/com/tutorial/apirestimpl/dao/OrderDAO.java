package com.tutorial.apirestimpl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.apirestimpl.models.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {

}
