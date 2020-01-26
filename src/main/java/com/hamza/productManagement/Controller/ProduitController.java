package com.hamza.productManagement.Controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.hamza.productManagement.Dao.ProduitDao;
import com.hamza.productManagement.Model.produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/produits")
public class ProduitController {
    @Autowired
    private ProduitDao produitDao;

    /*
    filtered response
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public MappingJacksonValue findAllProducts() {

        List<produit> Allproducts = produitDao.findAll();

        SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("prix");

        FilterProvider ListFiltres = new SimpleFilterProvider().addFilter("myFilter", myFilter);

        MappingJacksonValue produitFiltres = new MappingJacksonValue(Allproducts);

        produitFiltres.setFilters(ListFiltres);

        return produitFiltres;
    }

    @GetMapping(value = "/{id}")
    public produit findOne(@PathVariable int id) {
        return produitDao.getById(id);

    }

    @GetMapping(value = "/{prix}")
    public List<produit> findbyprice(@PathVariable int prix) {
        return produitDao.findAllByPrixOrderByPrixAsc(prix);
    }


    @PostMapping(value = "/add", consumes = "application/json")

    public ResponseEntity<Void> addProduct(@RequestBody produit p) {

        produit addedProduct = produitDao.save(p);
        if (addedProduct == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.
                fromHttpUrl("http://localhost:8080/produits").
                path("/{id}").buildAndExpand(addedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


}
