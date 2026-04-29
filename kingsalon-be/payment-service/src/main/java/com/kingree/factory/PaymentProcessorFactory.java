package com.kingree.factory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kingree.domain.PaymentMethod;
import com.kingree.processor.PaymentProcessor;

@Service
public class PaymentProcessorFactory {

    private final Map<PaymentMethod, PaymentProcessor> processors;

    public PaymentProcessorFactory(List<PaymentProcessor> processorList) {
        this.processors = processorList.stream()
                .collect(Collectors.toMap(PaymentProcessor::getType, p -> p));
    }

    public PaymentProcessor get(PaymentMethod type) {
        return processors.get(type);
    }
}