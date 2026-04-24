package com.kingree.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import com.kingree.dto.event.PaymentOrderEvent;
import com.kingree.service.BookingService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BookingEventConsumer {

    private final BookingService bookingService;

    @RabbitListener(queues = "booking-queue")
    public void bookingUpdateListener(PaymentOrderEvent paymentOrderEvent) throws Exception {
        bookingService.bookingSuccess(paymentOrderEvent);
    }
}
