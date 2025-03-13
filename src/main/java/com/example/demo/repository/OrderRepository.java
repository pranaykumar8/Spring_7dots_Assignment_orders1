package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);

//	List<Object[]> getTotalRevenuePerCustomer();
//
//	List<Object[]> getTotalRefundsPerCustomer();
//
//	List<Object[]> getCustomersPerMonth();
//
//	List<Object[]> getProductSalesData();
//
//	List<Order> findCompletedOrders();

	//List<Order> findCompletedOrders();

}
