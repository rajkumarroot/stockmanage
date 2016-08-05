/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.dao.inter;

import com.app.billingapp.model.Vendors;
import java.util.List;

/**
 *
 * @author node
 */
public interface VendorDao {
    Vendors findById(int id);
    List<Vendors> findAll();
    void save(Vendors vendors);
    void update(Vendors vendors);
    void delete(int id);
}
