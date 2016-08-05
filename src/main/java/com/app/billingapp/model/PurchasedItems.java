/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author node
 */
@Entity
@Table(name = "purchased_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchasedItems.findAll", query = "SELECT p FROM PurchasedItems p")})
public class PurchasedItems implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_id")
    private Integer itemId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_amount")
    private BigDecimal baseAmount;
    @Column(name = "pruchased_qty")
    private Integer pruchasedQty;
    @Size(max = 45)
    @Column(name = "batch_id")
    private String batchId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_tax_amount")
    private BigDecimal baseTaxAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_discount")
    private BigDecimal baseDiscount;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private Products productId;
    @JoinColumn(name = "purchase_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PurchaseMaster purchaseId;

    public PurchasedItems() {
    }

    public PurchasedItems(Integer itemId) {
        this.itemId = itemId;
    }

    public PurchasedItems(Integer itemId, BigDecimal baseAmount, BigDecimal baseTaxAmount, BigDecimal baseDiscount) {
        this.itemId = itemId;
        this.baseAmount = baseAmount;
        this.baseTaxAmount = baseTaxAmount;
        this.baseDiscount = baseDiscount;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public Integer getPruchasedQty() {
        return pruchasedQty;
    }

    public void setPruchasedQty(Integer pruchasedQty) {
        this.pruchasedQty = pruchasedQty;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public BigDecimal getBaseTaxAmount() {
        return baseTaxAmount;
    }

    public void setBaseTaxAmount(BigDecimal baseTaxAmount) {
        this.baseTaxAmount = baseTaxAmount;
    }

    public BigDecimal getBaseDiscount() {
        return baseDiscount;
    }

    public void setBaseDiscount(BigDecimal baseDiscount) {
        this.baseDiscount = baseDiscount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    public PurchaseMaster getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(PurchaseMaster purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchasedItems)) {
            return false;
        }
        PurchasedItems other = (PurchasedItems) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.billingapp.model.PurchasedItems[ itemId=" + itemId + " ]";
    }
    
}
