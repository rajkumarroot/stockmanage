/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.service.impl;

import com.app.billingapp.dao.AbstractDao;
import com.app.billingapp.dao.inter.VendorDao;
import com.app.billingapp.model.Vendors;
import com.app.billingapp.service.VendorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author node
 */
@Service("vendorService")
@Transactional
public class VendorServiceImpl extends AbstractDao<Integer, Vendors> implements VendorService{

    @Autowired
    VendorDao vendorDao;
    
    @Override
    public Vendors findById(int id) {
        return vendorDao.findById(id);
    }

    @Override
    public List<Vendors> findAll() {
        return vendorDao.findAll();
    }

    @Override
    public void save(Vendors vendors) {
        vendorDao.save(vendors);
    }

    @Override
    public void update(Vendors vendors) {
        vendorDao.update(vendors);
    }

    @Override
    public void delete(int id) {
        vendorDao.delete(id);
    }
    
}
