/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.service.impl;

import com.app.billingapp.dao.AbstractDao;
import com.app.billingapp.dao.inter.OrderDao;
import com.app.billingapp.model.OrderMaster;
import com.app.billingapp.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author node
 */
@Service ("orderService")
@Transactional
public class OrderServiceImpl extends AbstractDao<Integer, OrderMaster> implements OrderService{
    
    @Autowired
    OrderDao orderDao;

    @Override
    public OrderMaster findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public List<OrderMaster> findAll() {
        return orderDao.findAll();
    }

    @Override
    public void save(OrderMaster orderMaster) {
        orderDao.save(orderMaster);
    }

    @Override
    public void update(OrderMaster orderMaster) {
        orderDao.update(orderMaster);
    }
    
}
