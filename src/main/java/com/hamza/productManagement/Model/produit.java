package com.hamza.productManagement.Model;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonFilter("myFilter")
public class produit {
    public produit() {
    }

    public produit(int id, String nom, float prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Length(min = 0,max = 4,message = "le nombre doit etre compris entre 0 et 4 caractere")
    private String nom;
    private float prix;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }
}

