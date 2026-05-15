package com.kingree.service.client;

import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kingree.payload.dto.ServiceOfferingDTO;
import com.kingree.payload.response.PageResponse;

@FeignClient("service-offering")
public interface ServiceOfferingFeignClient {
    @GetMapping("api/service-offering/list/{ids}")
    public ResponseEntity<PageResponse<ServiceOfferingDTO>> getServiceByIds(@PathVariable Set<Long> ids)
            throws Exception;
}
