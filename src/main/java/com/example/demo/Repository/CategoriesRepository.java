package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Categories;

public interface CategoriesRepository  extends JpaRepository<Categories, Integer> {

}
