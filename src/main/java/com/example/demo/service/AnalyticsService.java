//package com.example.demo.service;
//
//import com.example.demo.model.Customer;
//import com.example.demo.model.Order;
//import com.example.demo.model.OrderItem;
//import com.example.demo.repository.CustomerRepository;
//import com.example.demo.repository.OrderRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class AnalyticsService {
//    private final OrderRepository orderRepository;
//    private final CustomerRepository customerRepository;
//
//    public AnalyticsService(OrderRepository orderRepository, CustomerRepository customerRepository) {
//        this.orderRepository = orderRepository;
//        this.customerRepository = customerRepository;
//    }
//
//    // 1️⃣ Most Profitable Customers (After Refunds)
//    public List<Map<String, Object>> getMostProfitableCustomers() {
//        List<Object[]> revenues = orderRepository.getTotalRevenuePerCustomer();
//        List<Object[]> refunds = orderRepository.getTotalRefundsPerCustomer();
//
//        // Convert to Maps
//        Map<Long, Double> revenueMap = revenues.stream()
//                .collect(Collectors.toMap(row -> (Long) row[0], row -> (Double) row[1]));
//
//        Map<Long, Double> refundMap = refunds.stream()
//                .collect(Collectors.toMap(row -> (Long) row[0], row -> (Double) row[1], (a, b) -> a));
//
//        // Calculate net revenue
//        List<Map<String, Object>> result = new ArrayList<>();
//        revenueMap.forEach((customerId, revenue) -> {
//            double refundedAmount = refundMap.getOrDefault(customerId, 0.0);
//            double netRevenue = revenue - refundedAmount;
//
//            customerRepository.findById(customerId).ifPresent(customer -> {
//                Map<String, Object> customerData = new HashMap<>();
//                customerData.put("Customer Name", customer.getName());
//                customerData.put("Total Spent", revenue);
//                customerData.put("Refunded Amount", refundedAmount);
//                customerData.put("Net Revenue", netRevenue);
//                result.add(customerData);
//            });
//        });
//
//        // Sort and return top 3 profitable customers
//        return result.stream()
//                .sorted(Comparator.comparingDouble(a -> -((Double) a.get("Net Revenue"))))
//                .limit(3)
//                .collect(Collectors.toList());
//    }
//
//    // 2️⃣ Monthly Retention Rate
//    public List<Map<String, Object>> calculateMonthlyRetention() {
//        List<Object[]> customerOrders = orderRepository.getCustomersPerMonth();
//        Map<String, Set<Long>> monthToCustomers = new HashMap<>();
//
//        // Group customers by month
//        for (Object[] row : customerOrders) {
//            String month = (String) row[0];
//            Long customerId = (Long) row[1];
//            monthToCustomers.computeIfAbsent(month, k -> new HashSet<>()).add(customerId);
//        }
//
//        List<Map<String, Object>> result = new ArrayList<>();
//        List<String> months = new ArrayList<>(monthToCustomers.keySet());
//        Collections.sort(months);
//
//        // Calculate retention
//        for (int i = 1; i < months.size(); i++) {
//            String prevMonth = months.get(i - 1);
//            String currentMonth = months.get(i);
//            Set<Long> returningCustomers = new HashSet<>(monthToCustomers.get(prevMonth));
//            returningCustomers.retainAll(monthToCustomers.get(currentMonth));
//
//            Map<String, Object> retentionData = new HashMap<>();
//            retentionData.put("Month", currentMonth);
//            retentionData.put("Returning Customers", returningCustomers.size());
//            retentionData.put("Total Customers", monthToCustomers.get(currentMonth).size());
//            retentionData.put("Retention Rate", (double) returningCustomers.size() / monthToCustomers.get(currentMonth).size() * 100);
//            result.add(retentionData);
//        }
//        return result;
//    }
//
//    // 3️⃣ Top Selling Products
//    public List<Map<String, Object>> getTopSellingProducts() {
//        List<Object[]> salesData = orderRepository.getProductSalesData();
//
//        return salesData.stream()
//                .map(row -> {
//                    Map<String, Object> productData = new HashMap<>();
//                    productData.put("Product Name", row[0]);
//                    productData.put("Total Quantity Sold", row[1]);
//                    return productData;
//                })
//                .sorted((a, b) -> (Integer) b.get("Total Quantity Sold") - (Integer) a.get("Total Quantity Sold"))
//                .limit(5)
//                .collect(Collectors.toList());
//    }
//
//    // 4️⃣ Average Order Value
//    public double getAverageOrderValue() {
//        List<Order> allOrders = orderRepository.findAll();
//        return allOrders.stream()
//                .mapToDouble(Order::getTotalAmount)
//                .average()
//                .orElse(0.0);
//    }
//
//    // 5️⃣ Order Processing Time Analysis
//    public Map<String, Object> getOrderProcessingTimeStats() {
//        List<Order> completedOrders = orderRepository.findCompletedOrders();
//
//        List<Long> processingTimes = completedOrders.stream()
//                .map(order -> ChronoUnit.DAYS.between(order.getOrderDate(), order.getDeliveryDate()))
//                .collect(Collectors.toList());
//
//        double avgProcessingTime = processingTimes.stream().mapToLong(Long::longValue).average().orElse(0);
//        long minProcessingTime = processingTimes.stream().mapToLong(Long::longValue).min().orElse(0);
//        long maxProcessingTime = processingTimes.stream().mapToLong(Long::longValue).max().orElse(0);
//
//        Map<String, Object> stats = new HashMap<>();
//        stats.put("Average Processing Time (Days)", avgProcessingTime);
//        stats.put("Min Processing Time (Days)", minProcessingTime);
//        stats.put("Max Processing Time (Days)", maxProcessingTime);
//
//        return stats;
//    }
//}
//
