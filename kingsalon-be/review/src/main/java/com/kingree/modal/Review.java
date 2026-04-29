package com.kingree.modal;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Review {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String reviewText;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private Long salonId;

    @Column(nullable = false)
    private Long userId;

    @CreatedDate
    private LocalDateTime createdAt;
}
