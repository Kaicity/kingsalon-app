package com.kingree.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kingree.mapper.NotificationMapper;
import com.kingree.modal.Notification;
import com.kingree.payload.dto.BookingDTO;
import com.kingree.payload.dto.NotificationDTO;
import com.kingree.repository.NotificationRepository;
import com.kingree.service.NotificationService;
import com.kingree.service.client.BookingFeignClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationMapper notificationMapper;

    private final NotificationRepository notificationRepository;
    private final BookingFeignClient bookingFeignClient;

    @Override
    public NotificationDTO createNotification(Notification notification) throws Exception {
        Notification createNotification = notificationRepository.save(notification);

        BookingDTO bookingDTO = bookingFeignClient.getBookingById(createNotification.getBookingId()).getBody();

        NotificationDTO notificationDTO = notificationMapper.mapToDTO(createNotification, bookingDTO);
        notificationDTO.setBookingDTO(bookingDTO);

        return notificationDTO;
    }

    @Override
    public List<NotificationDTO> getAllNotificationByUserId(Long userId) {
        return notificationRepository.findByUserId(userId).stream()
                .map(notification -> {
                    try {
                        BookingDTO bookingDTO = bookingFeignClient
                                .getBookingById(notification.getBookingId()).getBody();

                        NotificationDTO notificationDTO = notificationMapper.mapToDTO(notification, bookingDTO);
                        notificationDTO.setBookingDTO(bookingDTO);

                        return notificationDTO;
                    } catch (Exception e) {
                        return notificationMapper.mapToDTO(notification, null);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getAllNotificationBySalonId(Long salonId) {
        return notificationRepository.findBySalonId(salonId).stream()
                .map(notification -> {
                    try {
                        BookingDTO bookingDTO = bookingFeignClient
                                .getBookingById(notification.getBookingId()).getBody();

                        NotificationDTO notificationDTO = notificationMapper.mapToDTO(notification, bookingDTO);
                        notificationDTO.setBookingDTO(bookingDTO);

                        return notificationDTO;
                    } catch (Exception e) {
                        return notificationMapper.mapToDTO(notification, null);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDTO markNotificationAsRead(Long id) throws Exception {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new Exception("Notification not found with id = " + id));

        notification.setIsRead(true);

        return notificationMapper.mapToDTO(
                notificationRepository.save(notification),
                null);
    }

}
