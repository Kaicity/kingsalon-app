package com.kingree.service.imp;

import com.kingree.domain.PaymentMethod;
import com.kingree.modal.PaymentOrder;
import com.kingree.payload.dto.BookingDTO;
import com.kingree.payload.dto.UserDTO;
import com.kingree.payload.response.PaymentLinkResponse;
import com.kingree.repository.PaymentRepository;
import com.kingree.service.PaymentService;
import com.razorpay.PaymentLink;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Value("${razorpay.api.key}")
    private String razorpayApiKey;

    @Value("${razorpay.api.secret}")
    private String razorpayApiSecret;

    @Override
    public PaymentLinkResponse createOrder(UserDTO userDTO, BookingDTO bookingDTO, PaymentMethod paymentMethod) {
        return null;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) {
        return null;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(Long paymentId) {
        return null;
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(UserDTO userDTO, Long amount, Long orderId) {
        return null;
    }

    @Override
    public String createStripePaymentLink(UserDTO userDTO, Long amount, Long orderId) {
        return "";
    }
}
