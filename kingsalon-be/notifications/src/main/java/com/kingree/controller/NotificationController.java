package com.kingree.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kingree.modal.Notification;
import com.kingree.payload.dto.NotificationDTO;
import com.kingree.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody Notification notification) throws Exception {
        return ResponseEntity.ok(notificationService.createNotification(notification));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getAllNotificationByUserId(
            @PathVariable Long userId) {
        System.out.print(userId);
        return ResponseEntity.ok(notificationService.getAllNotificationByUserId(userId));
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<NotificationDTO>> getAllNotificationBySalonId(
            @PathVariable Long salonId) {
        return ResponseEntity.ok(notificationService.getAllNotificationBySalonId(salonId));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<NotificationDTO> getMarkNotificationAsRead(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(notificationService.markNotificationAsRead(id));
    }
}
