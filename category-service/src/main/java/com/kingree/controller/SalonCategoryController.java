package com.kingree.controller;

import com.kingree.dto.SalonDTO;
import com.kingree.modal.Category;
import com.kingree.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories/salon-owner")
public class SalonCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory (@RequestBody Category category) {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        Category newCategory = categoryService.saveCategory(category, salonDTO);

        return ResponseEntity.ok(newCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> createCategory (@PathVariable("id") Long id) throws Exception {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);

        categoryService.deleteCategoryById(id, salonDTO.getId());

        return ResponseEntity.ok("Category delete is successfully");
    }
}
