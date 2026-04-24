package com.kingree.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.kingree.modal.PaymentOrder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookingEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendBookingUpdateEvent(PaymentOrder paymentOrder) {
        rabbitTemplate.convertAndSend("booking-queue", paymentOrder);
    }
}
