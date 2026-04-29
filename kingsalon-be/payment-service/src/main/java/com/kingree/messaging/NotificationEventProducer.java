package com.kingree.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.kingree.payload.dto.NotificationDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationEventProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendNotification(Long bookingId, Long userId, Long salonId) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setBookingId(bookingId);
        notificationDTO.setUserId(userId);
        notificationDTO.setSalonId(salonId);
        notificationDTO.setDescription("New booking got confirmed");
        notificationDTO.setType("BOOKING");
        rabbitTemplate.convertAndSend("notification-queue", notificationDTO);
    }
}
