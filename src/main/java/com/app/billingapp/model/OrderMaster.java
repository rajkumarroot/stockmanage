/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author node
 */
@Entity
@Table(name = "order_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderMaster.findAll", query = "SELECT o FROM OrderMaster o")})
public class OrderMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "entity_id")
    private Integer entityId;
    @Column(name = "ordered_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderedDate = new Date();
    @JoinColumn(name = "order_placed_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User orderPlacedBy;
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id")
    @ManyToOne(optional = false)
    private Vendors vendorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderAgainstId")
    private List<PurchaseMaster> purchaseMasterList;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "orderId")
    private List<OrderItems> orderItemsList;
    
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "orderAgainstId")
    private Set<PurchaseMaster> purchaseMasterListSave;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "orderId")
    private Set<OrderItems> orderItemsListSave;

    public OrderMaster() {
    }

    public OrderMaster(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public User getOrderPlacedBy() {
        return orderPlacedBy;
    }

    public void setOrderPlacedBy(User orderPlacedBy) {
        this.orderPlacedBy = orderPlacedBy;
    }

    public Vendors getVendorId() {
        return vendorId;
    }

    public void setVendorId(Vendors vendorId) {
        this.vendorId = vendorId;
    }

    @XmlTransient
    public List<PurchaseMaster> getPurchaseMasterList() {
        return purchaseMasterList;
    }

    public void setPurchaseMasterList(List<PurchaseMaster> purchaseMasterList) {
        this.purchaseMasterList = purchaseMasterList;
    }

    @XmlTransient
    public List<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public Set<PurchaseMaster> getPurchaseMasterListSave() {
        return purchaseMasterListSave;
    }

    public Set<OrderItems> getOrderItemsListSave() {
        return orderItemsListSave;
    }
    public void setOrderItemsListSave(Set<OrderItems> orderItemsListSave) {
        this.orderItemsListSave = orderItemsListSave;
    }
    public void addItem(OrderItems item){
        if(this.orderItemsListSave == null){
            this.orderItemsListSave = new HashSet<OrderItems>();
        }
        item.setOrderId(this);
        item.setProductId(item.getProductId());
        this.orderItemsListSave.add(item);
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
        if (!(object instanceof OrderMaster)) {
            return false;
        }
        OrderMaster other = (OrderMaster) object;
        if ((this.entityId == null && other.entityId != null) || (this.entityId != null && !this.entityId.equals(other.entityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.billingapp.model.OrderMaster[ entityId=" + entityId + " ]";
    }
    
}
