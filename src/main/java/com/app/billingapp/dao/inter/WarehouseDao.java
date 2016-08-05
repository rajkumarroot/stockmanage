/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.dao.inter;

import com.app.billingapp.model.Warehouse;

/**
 *
 * @author node
 */
public interface WarehouseDao {
    Warehouse findByProduct(int id);
    Warehouse findByBatch(String batch);
    void save(Warehouse warehouse);
    void update(Warehouse warehouse);
}
