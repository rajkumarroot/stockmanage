/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.service;

import com.app.billingapp.model.OrderMaster;
import java.util.List;

/**
 *
 * @author node
 */
public interface OrderService {
    OrderMaster findById(int id);
    List<OrderMaster> findAll();
    void save(OrderMaster orderMaster);
    void update(OrderMaster orderMaster); 
}
