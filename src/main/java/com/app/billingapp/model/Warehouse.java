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
@Table(name = "warehouse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Warehouse.findAll", query = "SELECT w FROM Warehouse w")})
public class Warehouse implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WarehousePK warehousePK;
    @Column(name = "qty")
    private Integer qty;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Products products;

    public Warehouse() {
    }

    public Warehouse(WarehousePK warehousePK) {
        this.warehousePK = warehousePK;
    }

    public Warehouse(int productId, String batchId) {
        this.warehousePK = new WarehousePK(productId, batchId);
    }

    public WarehousePK getWarehousePK() {
        return warehousePK;
    }

    public void setWarehousePK(WarehousePK warehousePK) {
        this.warehousePK = warehousePK;
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
        hash += (warehousePK != null ? warehousePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Warehouse)) {
            return false;
        }
        Warehouse other = (Warehouse) object;
        if ((this.warehousePK == null && other.warehousePK != null) || (this.warehousePK != null && !this.warehousePK.equals(other.warehousePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.billingapp.model.Warehouse[ warehousePK=" + warehousePK + " ]";
    }
    
}
