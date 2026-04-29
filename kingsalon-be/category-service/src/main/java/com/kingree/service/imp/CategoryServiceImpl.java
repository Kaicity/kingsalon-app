package com.kingree.service.imp;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.kingree.dto.SalonDTO;
import com.kingree.modal.Category;
import com.kingree.repository.CategoryRepository;
import com.kingree.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category, SalonDTO salonDTO) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setImage(category.getImage());
        newCategory.setSalonId(salonDTO.getId());

        return categoryRepository.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoriesBySalon(Long id) {
        return categoryRepository.findBySalonId(id);
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElse(null);

        if(category == null) {
            throw new Exception("Category not exist with id " + id);
        }

        return category;
    }

    @Override
    public void deleteCategoryById(Long id, Long salonId) throws Exception {
       Category existingCategory = categoryRepository.findById(id).orElse(null);

        if(existingCategory == null){
            throw new Exception("Can not exist category with id " + id + " to delete");
        }

        if(!existingCategory.getSalonId().equals(salonId)){
            throw new Exception("You don't have permission to delete this category");
        }

        categoryRepository.deleteById(id);
    }

    @Override
    public Category findByIdAndSalonId(Long id, Long salonId) throws Exception {
        
        Category category =  categoryRepository.findByIdAndSalonId(id, salonId);

        if(category == null ){
            throw new Exception("Category not found");
        }

        return category;
    }
}
