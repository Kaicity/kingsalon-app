package com.kingree.processor.impl;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kingree.domain.PaymentMethod;
import com.kingree.domain.PaymentOrderStatus;
import com.kingree.messaging.BookingEventProducer;
import com.kingree.messaging.NotificationEventProducer;
import com.kingree.modal.PaymentOrder;
import com.kingree.payload.dto.UserDTO;
import com.kingree.payload.response.PaymentLinkResponse;
import com.kingree.processor.PaymentProcessor;
import com.kingree.repository.PaymentRepository;
import com.kingree.utils.MoMoUtils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MomoProcessor implements PaymentProcessor {

    private final BookingEventProducer bookingEventProducer;
    private final NotificationEventProducer notificationEventProducer;
    private final PaymentRepository paymentRepository;

    @Override
    public PaymentMethod getType() {
        return PaymentMethod.MOMO;
    }

    @Override
    public PaymentLinkResponse createPayment(UserDTO user, Long amount, Long orderId) throws Exception {
        String endpoint = "https://test-payment.momo.vn/v2/gateway/api/create";

        String partnerCode = "MOMO";
        String accessKey = "F8BBA842ECF85";
        String secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz";

        String orderInfo = "Pay booking salon with MOMO";
        String redirectUrl = "http://localhost:3000/payment-success";
        String ipnUrl = "http://localhost:5000/api/payments/momo/ipn";

        String requestId = String.valueOf(System.currentTimeMillis());
        String extraData = "";
        String orderIdStr = String.valueOf(orderId);
        String requestType = "payWithMethod";

        String rawHash = "accessKey=" + accessKey +
                "&amount=" + amount +
                "&extraData=" + extraData +
                "&ipnUrl=" + ipnUrl +
                "&orderId=" + orderIdStr +
                "&orderInfo=" + orderInfo +
                "&partnerCode=" + partnerCode +
                "&redirectUrl=" + redirectUrl +
                "&requestId=" + requestId +
                "&requestType=" + requestType;

        String signature = MoMoUtils.HmacSHA256(rawHash, secretKey);

        JSONObject body = new JSONObject();
        body.put("partnerCode", partnerCode);
        body.put("accessKey", accessKey);
        body.put("requestId", requestId);
        body.put("extraData", extraData);
        body.put("amount", amount.toString());
        body.put("orderId", orderIdStr);
        body.put("orderInfo", orderInfo);
        body.put("redirectUrl", redirectUrl);
        body.put("ipnUrl", ipnUrl);
        body.put("requestType", requestType);
        body.put("signature", signature);
        body.put("lang", "en");

        // API MOMO
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(endpoint, request, String.class);

        // PARSE RESPONSE
        JSONObject responseJson = new JSONObject(response.getBody());

        String payUrl = responseJson.getString("payUrl");

        PaymentLinkResponse res = new PaymentLinkResponse();
        res.setPayment_link_url(payUrl);

        return res;
    }

    @Override
    public boolean verifyAndProcess(PaymentOrder order, Long paymentId) throws Exception {
        return false;
    }

    public void handleIPN(JSONObject data) {

        Long orderId = Long.valueOf(data.getString("orderId"));
        int resultCode = data.getInt("resultCode");

        PaymentOrder order = paymentRepository.findById(orderId).orElseThrow();

        if (resultCode == 0) {
            // success
            bookingEventProducer.sendBookingUpdateEvent(order);
            notificationEventProducer.sendNotification(
                    order.getBookingId(),
                    order.getUserId(),
                    order.getSalonId());

            order.setStatus(PaymentOrderStatus.SUCCESS);
        } else {
            order.setStatus(PaymentOrderStatus.FAILED);
        }

        paymentRepository.save(order);
    }

}
