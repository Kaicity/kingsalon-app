package com.kingree.service;

import com.kingree.modal.Category;
import com.kingree.payload.dto.SalonDTO;

import java.util.Set;

public interface CategoryService {

    Category saveCategory(Category category, SalonDTO salonDTO);

    Set<Category> getAllCategoriesBySalon(Long id);

    Category getCategoryById(Long id) throws Exception;

    void deleteCategoryById(Long id, Long salonId) throws Exception;

    Category findByIdAndSalonId(Long id, Long salonId) throws Exception;
}
