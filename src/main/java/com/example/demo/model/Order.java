package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference // Child side
    private Customer customer;
    
    private LocalDateTime orderDate;
    private String status;
    private Double totalAmount;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
//
//	public Temporal getDeliveryDate() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}