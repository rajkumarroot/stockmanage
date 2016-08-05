/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.dao.impl;

import com.app.billingapp.dao.AbstractDao;
import com.app.billingapp.dao.inter.ProductDao;
import com.app.billingapp.model.Products;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author node
 */
@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Products> implements ProductDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Products findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<Products> findAll() {
        List<Products> products = null;
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        products = crit.list();
        return products;
    }

    @Override
    public void save(Products product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public void update(Products product) {
        sessionFactory.getCurrentSession().update(product);
    }
    
}
