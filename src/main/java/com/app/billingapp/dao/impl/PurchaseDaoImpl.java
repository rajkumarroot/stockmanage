/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.dao.impl;

import com.app.billingapp.dao.AbstractDao;
import com.app.billingapp.dao.inter.PurchaseDao;
import com.app.billingapp.model.PurchaseMaster;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author node
 */
@Repository ("purchaseDoa")
public class PurchaseDaoImpl extends AbstractDao<Integer, PurchaseMaster> implements PurchaseDao{

    @Autowired
    SessionFactory sessionFactory;
    
    @Override
    public PurchaseMaster findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<PurchaseMaster> findAll() {
        List<PurchaseMaster> purchaseMasters = null;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        purchaseMasters = criteria.list();
        return purchaseMasters;
    }

    @Override
    public void save(PurchaseMaster purchaseMaster) {
        sessionFactory.getCurrentSession().save(purchaseMaster);
    }

    @Override
    public void update(PurchaseMaster purchaseMaster) {
        sessionFactory.getCurrentSession().update(purchaseMaster);
    }

    @Override
    public void delete(String deleteBy, int id) {
        sessionFactory.getCurrentSession().delete(deleteBy, id);
    }
    
}
