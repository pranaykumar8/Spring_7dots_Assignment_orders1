package com.example.demo;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderItemRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class DataSeeder implements CommandLineRunner {
    
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final Faker faker = new Faker();
    private final List<String> categories = Arrays.asList("Electronics", "Clothing", "Books", "Home & Kitchen", "Toys");

    public DataSeeder(CustomerRepository customerRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void run(String... args) {
        seedCustomers();
        seedOrders();
    }

    private void seedCustomers() {
        if (customerRepository.count() > 0) return; // Skip if data already exists

        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Customer customer = new Customer();
            customer.setName(faker.name().fullName());
            customer.setEmail(faker.internet().emailAddress());
            customer.setCreatedAt(LocalDateTime.now().minusDays(faker.number().numberBetween(10, 365)));
            customers.add(customer);
        }
        customerRepository.saveAll(customers);
        System.out.println("100 Customers inserted.");
    }

    private void seedOrders() {
        if (orderRepository.count() > 0) return; // Skip if data already exists

        List<Customer> customers = customerRepository.findAll();
        List<Order> orders = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();
        
        for (int i = 0; i < 500; i++) {
            Customer randomCustomer = customers.get(faker.number().numberBetween(0, customers.size()));
            Order order = new Order();
            order.setCustomer(randomCustomer);
            order.setOrderDate(LocalDateTime.now().minusDays(faker.number().numberBetween(1, 365)));
            order.setStatus(randomStatus());
            order.setTotalAmount(0.0); // Will be updated after adding order items
            orders.add(order);
        }
        orderRepository.saveAll(orders);

        for (Order order : orders) {
            int itemCount = faker.number().numberBetween(2, 10);
            double totalAmount = 0.0;

            for (int j = 0; j < itemCount; j++) {
                OrderItem item = new OrderItem();
                item.setOrder(order);
                item.setProductName(faker.commerce().productName());
                item.setCategory(categories.get(faker.number().numberBetween(0, categories.size())));
                item.setQuantity(faker.number().numberBetween(1, 5));
                item.setPrice(faker.number().randomDouble(2, 10, 500));
                totalAmount += item.getPrice() * item.getQuantity();
                orderItems.add(item);
            }
            order.setTotalAmount(totalAmount);
        }
        orderItemRepository.saveAll(orderItems);
        orderRepository.saveAll(orders);
        System.out.println("500 Orders with OrderItems inserted.");
    }

    private String randomStatus() {
        List<String> statuses = Arrays.asList("PENDING", "SHIPPED", "DELIVERED", "CANCELED");
        return statuses.get(faker.number().numberBetween(0, statuses.size()));
    }
}

