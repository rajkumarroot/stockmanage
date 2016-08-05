/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.service;

import com.app.billingapp.model.Products;
import java.util.List;

/**
 *
 * @author node
 */
public interface ProductService {
    Products findById(int id);
    List<Products> findAll();
    void save(Products product);
    void update(Products product);
}
