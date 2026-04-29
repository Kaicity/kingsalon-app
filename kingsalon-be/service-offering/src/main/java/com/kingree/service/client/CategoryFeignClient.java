package com.kingree.service.client;

import com.kingree.dto.CategoryDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("category-service")
public interface CategoryFeignClient {

    @GetMapping("/api/categories/{id}")
    ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) throws Exception;

    @GetMapping("api/categories/salon-owner/salon/{salonId}/category/{id}")
    public ResponseEntity<CategoryDTO> getCategoryByIdAndSalon(@PathVariable Long salonId, @PathVariable Long id)
            throws Exception;
}
