package com.ecosun.repository;

import com.ecosun.model.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByUserUserId(Long userId);
}
