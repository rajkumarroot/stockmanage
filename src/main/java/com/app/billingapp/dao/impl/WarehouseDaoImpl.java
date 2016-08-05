/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.dao.impl;

import com.app.billingapp.dao.AbstractDao;
import com.app.billingapp.dao.inter.WarehouseDao;
import com.app.billingapp.model.Warehouse;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author node
 */
@Repository ("warehouseDao")
public class WarehouseDaoImpl extends AbstractDao<Integer, Warehouse> implements WarehouseDao{

    @Autowired
    SessionFactory sessionFactory;
    
    @Override
    public Warehouse findByProduct(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("product_id", id) );
        return (Warehouse) crit.list();
    }

    @Override
    public Warehouse findByBatch(String batch) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Warehouse warehouse) {
        sessionFactory.getCurrentSession().save(warehouse);
    }

    @Override
    public void update(Warehouse warehouse) {
        sessionFactory.getCurrentSession().update(warehouse);
    }
    
}
