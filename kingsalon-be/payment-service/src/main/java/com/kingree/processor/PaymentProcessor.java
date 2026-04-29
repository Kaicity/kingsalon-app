package com.kingree.processor;

import com.kingree.domain.PaymentMethod;
import com.kingree.modal.PaymentOrder;
import com.kingree.payload.dto.UserDTO;
import com.kingree.payload.response.PaymentLinkResponse;

public interface PaymentProcessor {
    PaymentMethod getType();

    PaymentLinkResponse createPayment(UserDTO user, Long amount, Long orderId) throws Exception;

    boolean verifyAndProcess(PaymentOrder order, Long paymentId) throws Exception;
}