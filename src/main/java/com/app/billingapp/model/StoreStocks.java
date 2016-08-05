/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author node
 */
@Entity
@Table(name = "store_stocks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreStocks.findAll", query = "SELECT s FROM StoreStocks s")})
public class StoreStocks implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoreStocksPK storeStocksPK;
    @Column(name = "qty")
    private Integer qty;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Products products;

    public StoreStocks() {
    }

    public StoreStocks(StoreStocksPK storeStocksPK) {
        this.storeStocksPK = storeStocksPK;
    }

    public StoreStocks(int productId, String batchId) {
        this.storeStocksPK = new StoreStocksPK(productId, batchId);
    }

    public StoreStocksPK getStoreStocksPK() {
        return storeStocksPK;
    }

    public void setStoreStocksPK(StoreStocksPK storeStocksPK) {
        this.storeStocksPK = storeStocksPK;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeStocksPK != null ? storeStocksPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreStocks)) {
            return false;
        }
        StoreStocks other = (StoreStocks) object;
        if ((this.storeStocksPK == null && other.storeStocksPK != null) || (this.storeStocksPK != null && !this.storeStocksPK.equals(other.storeStocksPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.billingapp.model.StoreStocks[ storeStocksPK=" + storeStocksPK + " ]";
    }
    
}
