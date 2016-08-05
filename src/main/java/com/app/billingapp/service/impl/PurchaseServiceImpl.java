/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.service.impl;

import com.app.billingapp.dao.AbstractDao;
import com.app.billingapp.dao.inter.PurchaseDao;
import com.app.billingapp.model.PurchaseMaster;
import com.app.billingapp.service.PurchaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author node
 */
@Service ("purchaseService")
@Transactional
public class PurchaseServiceImpl extends AbstractDao<Integer, PurchaseMaster> implements PurchaseService{

    @Autowired
    PurchaseDao purchaseDao;
    
    @Override
    public PurchaseMaster findById(int id) {
        return purchaseDao.findById(id);
    }

    @Override
    public List<PurchaseMaster> findAll() {
        return purchaseDao.findAll();
    }

    @Override
    public void save(PurchaseMaster purchaseMaster) {
        purchaseDao.save(purchaseMaster);
    }

    @Override
    public void update(PurchaseMaster purchaseMaster) {
        purchaseDao.update(purchaseMaster);
    }

    @Override
    public void delete(String deleteBy, int id) {
        purchaseDao.delete(deleteBy, id);
    }
    
}
