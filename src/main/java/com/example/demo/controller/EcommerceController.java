package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.service.EcommerceService;

@RestController
@RequestMapping
public class EcommerceController {

	 @Autowired
	    private EcommerceService ecommerceService;
	    
	    @PostMapping("/customers")
	    public Customer registerCustomer(@RequestBody Customer customer) {
	        return ecommerceService.registerCustomer(customer);
	    }
	    
	    @PostMapping("/orders")
	    public Order placeOrder(@RequestBody Order order) {
	        return ecommerceService.placeOrder(order);
	    }
	    
	    
	    @GetMapping("/orders")
	    public List<Order> getCustomerOrders(@RequestParam(required = false) Long customerId) {
	        if (customerId == null) {
	            return ecommerceService.getAllOrders(); // Fetch all orders if no customerId provided
	        }
	        return ecommerceService.getCustomerOrders(customerId);
	    }

	    
	    @GetMapping("/orders/{id}")
	    public Order getOrderById(@PathVariable Long id) {
	        return ecommerceService.getOrderById(id);
	    }
	    
	    @DeleteMapping("/orders/{id}")
	    public void cancelOrder(@PathVariable Long id) {
	        ecommerceService.cancelOrder(id);
	    }

}
