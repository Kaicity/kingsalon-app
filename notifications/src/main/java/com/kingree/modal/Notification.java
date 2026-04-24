package com.kingree.modal;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    private String description;

    private Boolean isRead = false;

    private Long userId;

    private Long bookingId;

    private Long salonId;

    @CreatedDate
    private LocalDateTime createdAt;

}
