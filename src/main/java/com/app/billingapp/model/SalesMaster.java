/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author node
 */
@Entity
@Table(name = "sales_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesMaster.findAll", query = "SELECT s FROM SalesMaster s")})
public class SalesMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "entity_id")
    private Integer entityId;
    @Column(name = "sales_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date salesDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_amount")
    private BigDecimal baseAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_tax_amount")
    private BigDecimal baseTaxAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_discount")
    private BigDecimal baseDiscount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_total")
    private BigDecimal baseTotal;
    @Size(max = 45)
    @Column(name = "user_id")
    private String userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesId")
    private List<SalesItems> salesItemsList;

    public SalesMaster() {
    }

    public SalesMaster(Integer entityId) {
        this.entityId = entityId;
    }

    public SalesMaster(Integer entityId, BigDecimal baseAmount, BigDecimal baseTaxAmount, BigDecimal baseDiscount, BigDecimal baseTotal) {
        this.entityId = entityId;
        this.baseAmount = baseAmount;
        this.baseTaxAmount = baseTaxAmount;
        this.baseDiscount = baseDiscount;
        this.baseTotal = baseTotal;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
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

    public BigDecimal getBaseTotal() {
        return baseTotal;
    }

    public void setBaseTotal(BigDecimal baseTotal) {
        this.baseTotal = baseTotal;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @XmlTransient
    public List<SalesItems> getSalesItemsList() {
        return salesItemsList;
    }

    public void setSalesItemsList(List<SalesItems> salesItemsList) {
        this.salesItemsList = salesItemsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entityId != null ? entityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesMaster)) {
            return false;
        }
        SalesMaster other = (SalesMaster) object;
        if ((this.entityId == null && other.entityId != null) || (this.entityId != null && !this.entityId.equals(other.entityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.billingapp.model.SalesMaster[ entityId=" + entityId + " ]";
    }
    
}
