package com.kingree.service.imp;

import org.springframework.stereotype.Service;

import com.kingree.domain.PaymentMethod;
import com.kingree.factory.PaymentProcessorFactory;
import com.kingree.modal.PaymentOrder;
import com.kingree.payload.dto.BookingDTO;
import com.kingree.payload.dto.UserDTO;
import com.kingree.payload.response.PaymentLinkResponse;
import com.kingree.repository.PaymentRepository;
import com.kingree.service.PaymentService;
import com.kingree.service.client.UserFeignClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserFeignClient userFeignClient;
    private final PaymentProcessorFactory factory;

    @Override
    public PaymentLinkResponse createOrder(String jwt, BookingDTO bookingDTO, PaymentMethod paymentMethod)
            throws Exception {

        UserDTO user = userFeignClient.getUserProfile(jwt).getBody();

        PaymentOrder order = new PaymentOrder();
        order.setAmount((long) bookingDTO.getTotalPrice());
        order.setPaymentMethod(paymentMethod);
        order.setBookingId(bookingDTO.getId());
        order.setSalonId(bookingDTO.getSalonId());
        order.setUserId(user.getId());

        PaymentOrder saved = paymentRepository.save(order);

        return factory.get(paymentMethod)
                .createPayment(user, saved.getAmount(), saved.getId());
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {
        PaymentOrder paymentOrder = paymentRepository.findById(id).orElse(null);
        if (paymentOrder == null) {
            throw new Exception("Payment order not found");
        }
        return paymentOrder;
    }

    @Override
    public Boolean proceedPayment(PaymentOrder paymentOrder, Long paymentId)
            throws Exception {

        return factory.get(paymentOrder.getPaymentMethod())
                .verifyAndProcess(paymentOrder, paymentId);
    }

}
