package com.hamza.productManagement.Controller;

import com.hamza.productManagement.Dao.ProduitDao;
import com.hamza.productManagement.Model.produit;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProduitController {
    @Autowired
    private ProduitDao produitDao;


    @RequestMapping(value = "/produits", method = RequestMethod.GET)
    public List<produit> findAllProducts() {
        return produitDao.findAll();
    }


    @GetMapping(value = "/produit/{id}")
    public produit findOne(@PathVariable int id) {
        return produitDao.getById(id);

    }

    @GetMapping(value = "/produit/{prix}")
    public List<produit> findbyprice(@PathVariable int prix) {
        return produitDao.findAllByPrixOrderByPrixAsc(prix);
    }


    @PostMapping(value = "produit/add", consumes = "application/json")

    public ResponseEntity<Void> addProduct(@RequestBody produit p) {

        produit addedProduct = produitDao.save(p);
        if (addedProduct == null) {
            return ResponseEntity.noContent().build();
        }
   Uri location = ServletUriComponentsBuilder.

        return ResponseEntity.created().build();
    }


}
