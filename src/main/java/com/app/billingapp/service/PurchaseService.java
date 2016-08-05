/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.service;

import com.app.billingapp.model.PurchaseMaster;
import java.util.List;

/**
 *
 * @author node
 */
public interface PurchaseService {
    PurchaseMaster findById(int id);
    List<PurchaseMaster> findAll();
    void save(PurchaseMaster purchaseMaster);
    void update(PurchaseMaster purchaseMaster);
    void delete(String deleteBy,int id);
}
