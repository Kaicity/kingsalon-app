package com.kingree.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kingree.modal.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Set<Category> findBySalonId(Long salonId);

    Category findByIdAndSalonId(Long id,  Long salonId);
}
