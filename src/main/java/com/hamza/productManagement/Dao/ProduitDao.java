package com.hamza.productManagement.Dao;

import com.hamza.productManagement.Model.produit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProduitDao extends JpaRepository<produit, Integer> {
    produit getById(int id);
    List<produit> findAll();
    List<produit> findAllByPrixOrderByPrixAsc(float prix);

}
