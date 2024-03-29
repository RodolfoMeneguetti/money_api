package com.algaworks.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.crm.model.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long>{

}
