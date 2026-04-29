package com.kingree.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingSlotDTO {
    LocalDateTime startTime;

    LocalDateTime endTime;
}
