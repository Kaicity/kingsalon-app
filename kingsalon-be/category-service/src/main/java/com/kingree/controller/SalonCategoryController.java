package com.kingree.controller;

import com.kingree.dto.SalonDTO;
import com.kingree.modal.Category;
import com.kingree.service.CategoryService;
import com.kingree.service.client.SalonFeignClient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories/salon-owner")
public class SalonCategoryController {

    private final CategoryService categoryService;
    private final SalonFeignClient salonFeignClient;

    @PostMapping
    public ResponseEntity<Category> createCategory(
            @RequestBody Category category,
            @RequestHeader("Authorization") String jwt) throws Exception {

        SalonDTO salonDTO = salonFeignClient.getSalonByOwnerId(jwt).getBody();
        Category newCategory = categoryService.saveCategory(category, salonDTO);

        return ResponseEntity.ok(newCategory);
    }

    @GetMapping("/salon/{salonId}/category/{id}")
    public ResponseEntity<Category> getCategoryByIdAndSalon(
            @PathVariable Long salonId,
            @PathVariable Long id) throws Exception {

        Category category = categoryService.findByIdAndSalonId(id, salonId);

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> createCategory(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {

        SalonDTO salonDTO = salonFeignClient.getSalonByOwnerId(jwt).getBody();

        categoryService.deleteCategoryById(id, salonDTO.getId());

        return ResponseEntity.ok("Category delete is successfully");
    }
}
