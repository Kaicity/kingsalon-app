package com.kingree.dto.event;

import com.kingree.domain.PaymentMethod;

import lombok.Data;

@Data
public class PaymentOrderEvent {
    private Long id;
    private Long amount;
    private PaymentMethod paymentMethod;
    private String paymentLinkId;
    private Long userId;
    private Long bookingId;
    private Long salonId;
}
