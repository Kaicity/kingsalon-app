package com.kingree.service;

import com.kingree.domain.PaymentMethod;
import com.kingree.modal.PaymentOrder;
import com.kingree.payload.dto.BookingDTO;
import com.kingree.payload.response.PaymentLinkResponse;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {

        PaymentLinkResponse createOrder(String jwt, BookingDTO bookingDTO, PaymentMethod paymentMethod)
                        throws RazorpayException, StripeException, Exception;

        PaymentOrder getPaymentOrderById(Long id) throws Exception;

        Boolean proceedPayment(PaymentOrder paymentOrder, Long paymentId)
                        throws RazorpayException, Exception;
}
