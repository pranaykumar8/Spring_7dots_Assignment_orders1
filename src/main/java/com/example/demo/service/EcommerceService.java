package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;

@Service
public class EcommerceService {

	 @Autowired
	    private CustomerRepository customerRepository;
	    
	    @Autowired
	    private OrderRepository  orderRepository;
	    
	    @Autowired
	    private OrderItemRepository orderItemRepository;

	    public Customer registerCustomer(Customer customer) {
	        customer.setCreatedAt(LocalDateTime.now());
	        return customerRepository.save(customer);
	    }

	    public Order placeOrder(Order order) {
	        order.setOrderDate(LocalDateTime.now());
	        order.setStatus("PENDING");
	        return orderRepository.save(order);
	    }

	    public List<Order> getCustomerOrders(Long customerId) {
	        return orderRepository.findByCustomerId(customerId);
	    }

	    public Order getOrderById(Long orderId) {
	        return orderRepository.findById(orderId).orElse(null);
	    }

	    public void cancelOrder(Long orderId) {
	        Order order = orderRepository.findById(orderId).orElse(null);
	        if (order != null && "PENDING".equals(order.getStatus())) {
	            order.setStatus("CANCELED");
	            orderRepository.save(order);
	        }
	    }

		public List<Order> getAllOrders() {
			// TODO Auto-generated method stub
			return null;
		}

}
