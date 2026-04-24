package com.kingree.processor.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kingree.domain.PaymentMethod;
import com.kingree.domain.PaymentOrderStatus;
import com.kingree.messaging.BookingEventProducer;
import com.kingree.messaging.NotificationEventProducer;
import com.kingree.modal.PaymentOrder;
import com.kingree.payload.dto.UserDTO;
import com.kingree.payload.response.PaymentLinkResponse;
import com.kingree.processor.PaymentProcessor;
import com.kingree.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StripeProcessor implements PaymentProcessor {

        @Value("${stripe.api.key}")
        private String secretKey;

        private final BookingEventProducer bookingEventProducer;
        private final NotificationEventProducer notificationEventProducer;
        private final PaymentRepository paymentRepository;

        @Override
        public PaymentMethod getType() {
                return PaymentMethod.STRIPE;
        }

        @Override
        public PaymentLinkResponse createPayment(UserDTO user, Long amount, Long orderId) throws StripeException {
                Stripe.apiKey = secretKey;

                Session session = Session.create(
                                SessionCreateParams.builder()
                                                .setMode(SessionCreateParams.Mode.PAYMENT)
                                                .setSuccessUrl("http://localhost:3000/payment-success/" + orderId)
                                                .setCancelUrl("http://localhost:3000/payment/cancel")
                                                .addLineItem(
                                                                SessionCreateParams.LineItem.builder()
                                                                                .setQuantity(1L)
                                                                                .setPriceData(
                                                                                                SessionCreateParams.LineItem.PriceData
                                                                                                                .builder()
                                                                                                                .setCurrency("vnd")
                                                                                                                .setUnitAmount(amount)
                                                                                                                .setProductData(
                                                                                                                                SessionCreateParams.LineItem.PriceData.ProductData
                                                                                                                                                .builder()
                                                                                                                                                .setName("Salon booking")
                                                                                                                                                .build())
                                                                                                                .build())
                                                                                .build())
                                                .build());

                PaymentLinkResponse res = new PaymentLinkResponse();
                res.setPayment_link_url(session.getUrl());
                return res;
        }

        @Override
        public boolean verifyAndProcess(PaymentOrder order, Long paymentId) {
                // ⚠ Stripe chuẩn là webhook, tạm mock success

                bookingEventProducer.sendBookingUpdateEvent(order);
                notificationEventProducer.sendNotification(
                                order.getBookingId(),
                                order.getUserId(),
                                order.getSalonId());

                order.setStatus(PaymentOrderStatus.SUCCESS);
                paymentRepository.save(order);

                return true;
        }
}