package com.kingree.modal;

import lombok.Data;

@Data
public class SalonReport {
    private Long salonId;
    private String salonName;
    private Double totalEarning;
    private Integer totalBooking;
    private Integer cancelledBooking;
    private Double totalRefund;
}
