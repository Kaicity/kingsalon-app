package com.kingree.service;

import java.util.List;

import com.kingree.modal.Notification;
import com.kingree.payload.dto.NotificationDTO;

public interface NotificationService {

    NotificationDTO createNotification(Notification notification) throws Exception;

    List<NotificationDTO> getAllNotificationByUserId(Long userId);

    List<NotificationDTO> getAllNotificationBySalonId(Long salonId);

    NotificationDTO markNotificationAsRead(Long id) throws Exception;
}
