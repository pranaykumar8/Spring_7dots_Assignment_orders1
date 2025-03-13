//package com.example.demo.controller;
//
//import com.example.demo.service.AnalyticsService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/analytics")
//public class AnalyticsController {
//    private final AnalyticsService analyticsService;
//
//    public AnalyticsController(AnalyticsService analyticsService) {
//        this.analyticsService = analyticsService;
//    }
//
//    @GetMapping("/profitable-customers")
//    public List<Map<String, Object>> getMostProfitableCustomers() {
//        return analyticsService.getMostProfitableCustomers();
//    }
//
//    @GetMapping("/monthly-retention")
//    public List<Map<String, Object>> getMonthlyRetentionRate() {
//        return analyticsService.calculateMonthlyRetention();
//    }
//
//    @GetMapping("/top-products")
//    public List<Map<String, Object>> getTopSellingProducts() {
//        return analyticsService.getTopSellingProducts();
//    }
//
//    @GetMapping("/avg-order-value")
//    public double getAverageOrderValue() {
//        return analyticsService.getAverageOrderValue();
//    }
//
//    @GetMapping("/order-processing-time")
//    public Map<String, Object> getOrderProcessingTimeStats() {
//        return analyticsService.getOrderProcessingTimeStats();
//    }
//}
//
