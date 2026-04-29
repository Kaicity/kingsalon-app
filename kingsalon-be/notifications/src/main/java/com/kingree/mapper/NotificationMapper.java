package com.kingree.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kingree.modal.Notification;
import com.kingree.payload.dto.BookingDTO;
import com.kingree.payload.dto.NotificationDTO;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "id", source = "notification.id")
    @Mapping(target = "type", source = "notification.type")
    @Mapping(target = "description", source = "notification.description")
    @Mapping(target = "isRead", source = "notification.isRead")
    @Mapping(target = "userId", source = "notification.userId")
    @Mapping(target = "bookingId", source = "notification.bookingId")
    @Mapping(target = "salonId", source = "notification.salonId")
    @Mapping(target = "createdAt", source = "notification.createdAt")
    NotificationDTO mapToDTO(Notification notification, BookingDTO bookingDTO);
}
