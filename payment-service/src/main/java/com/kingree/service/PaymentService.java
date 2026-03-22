package com.kingree.service;

import com.kingree.domain.PaymentMethod;
import com.kingree.modal.PaymentOrder;
import com.kingree.payload.dto.BookingDTO;
import com.kingree.payload.dto.UserDTO;
import com.kingree.payload.response.PaymentLinkResponse;
import com.razorpay.PaymentLink;

public interface PaymentService {

    PaymentLinkResponse createOrder(UserDTO userDTO, BookingDTO bookingDTO, PaymentMethod paymentMethod);

    PaymentOrder getPaymentOrderById(Long id);

    PaymentOrder getPaymentOrderByPaymentId(Long paymentId);

    PaymentLink createRazorpayPaymentLink(UserDTO userDTO, Long amount, Long orderId);

    String createStripePaymentLink(UserDTO userDTO, Long amount, Long orderId);
}
