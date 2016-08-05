/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.controllers;

import com.app.billingapp.model.OrderItems;
import com.app.billingapp.model.OrderMaster;
import com.app.billingapp.model.Products;
import com.app.billingapp.model.User;
import com.app.billingapp.model.Vendors;
import com.app.billingapp.service.OrderService;
import com.app.billingapp.service.ProductService;
import com.app.billingapp.service.VendorService;
import com.app.billingapp.utilities.HashSecure;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Rajkumar
 */
@Controller
public class PurchaseController {
    
    @Autowired
    VendorService vendorService;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    OrderService orderService;
    
    private HashSecure hashSecure;
    
    @RequestMapping(value = {"/admin/inventory/placeorder","/supervisor/inventory/placeorder","/user/inventory/placeorder"},method = RequestMethod.GET)
    public String purchaseOrderList(ModelMap model) throws UnsupportedEncodingException{
        List<OrderMaster> listOrdered = orderService.findAll();
        for(OrderMaster order:listOrdered){
            System.out.println(order.getEntityId()+": "+order.getVendorId().getName());
        }
        int entity_id = Integer.parseInt(URLEncoder.encode("1", "UTF-8"));
        System.out.println("Encoded string::"+entity_id);
        model.addAttribute("listorders", listOrdered);
        model.addAttribute("pageTitle","Order Placed List");
        return "purchase/purchaseorder";
    }
    @RequestMapping(value = {"/admin/inventory/purchaseinvoice","/supervisor/inventory/purchaseinvoice","/user/inventory/purchaseinvoice"},method = RequestMethod.GET)
    public String purchaseInvoiceList(ModelMap model){
        
        return "purchase/purchaseInvoice";
    }
    @RequestMapping(value = {"/admin/inventory/placeorder/add","/supervisor/inventory/placeorder/add","/user/inventory/placeorder/add"},method = RequestMethod.GET)
    public String placeOrder(ModelMap model,HttpSession session){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OrderMaster orderMaster = new OrderMaster();
        User loggedUser = (User) session.getAttribute("loggeduser");
        orderMaster.setOrderPlacedBy(loggedUser);
        model.addAttribute("pageTitle","Item Orders");
        model.addAttribute("editOrder", orderMaster);
        return "purchase/placeOrder";
    }
    
    @ModelAttribute("suppliers")
    public Map< Integer, String >  categoriesList(){
        Map< Integer, String > suppliers = new HashMap<Integer, String>();
        List<Vendors> suppliersModel = vendorService.findAll();
        for(Vendors supplier: suppliersModel){
            suppliers.put(supplier.getVendorId(), supplier.getName());
        }
        return suppliers;
    }
    @ModelAttribute("defaultorder")
    public OrderMaster defaultOrder(){
        OrderMaster orderMaster = new OrderMaster();
        
        return orderMaster;
    }
    
    @ModelAttribute("productsListName")
    public Map< Integer, String > listProductName(){
        Map< Integer, String > productsList = new HashMap<Integer, String>();
        List<Products> prodList = productService.findAll();
        for(Products prod: prodList){
            productsList.put(prod.getProductId(), prod.getName());
        }
        return productsList;
    }
    @ModelAttribute("productsListCode")
    public Map< Integer, String > listProductCode(){
        Map< Integer, String > productsList = new HashMap<Integer, String>();
        List<Products> prodList = productService.findAll();
        for(Products prod: prodList){
            productsList.put(prod.getProductId(), prod.getCode()+"-"+prod.getName());
        }
        return productsList;
    }
    
    @ModelAttribute("weightUnit")
    public Map< String, String > listWeight(){
        Map< String, String > listWeight = new HashMap<String, String>();
        listWeight.put("g","Gram");
        listWeight.put("kg","Kg");
        listWeight.put("lt","Litre");
        listWeight.put("ml","Milliliter");
        return listWeight;
    }
    @RequestMapping(value = {"/admin/inventory/placeorder/postOrder","/supervisor/inventory/placeorder/postOrder","/user/inventory/placeorder/postOrder"},method = RequestMethod.POST)
    public String placeOrderSave(@ModelAttribute("orderMaseter") OrderMaster orderMaseter,BindingResult result){
        System.out.println(orderMaseter.getOrderPlacedBy().getId());
        List<OrderItems> itemList = orderMaseter.getOrderItemsList();
        itemList.remove(0);
        orderMaseter.setOrderItemsList(itemList);
        for(OrderItems item:orderMaseter.getOrderItemsList()){
            orderMaseter.addItem(item);
        }
        orderService.save(orderMaseter);
        return "purchase/purchaseorder";
    }
    @RequestMapping(value = {"/admin/inventory/placeorder/viewOrder/{id}",
        "/supervisor/inventory/placeorder/viewOrder/{id}","/user/inventory/placeorder/viewOrder/{id}"},method = RequestMethod.GET)
    public String viewPlacedOrder(@PathVariable String id,ModelMap model) throws Exception{
        hashSecure = new HashSecure();
        int entity_id = Integer.parseInt(hashSecure.decrypt(id));
        OrderMaster itemOrder = orderService.findById(entity_id);
        System.out.println("Dycripted Id: "+itemOrder.getPurchaseMasterListSave());
        model.addAttribute("pageTitle","View Order Placed");
        model.addAttribute("itemDetails",itemOrder);
        return "purchase/viewOrderedItem";
    }
    /*
      Edit the placed order. 
    */
    @RequestMapping(value = {"/admin/inventory/placeorder/edit/{id}",
        "/supervisor/inventory/placeorder/edit/{id}","/user/inventory/placeorder/edit/{id}"},method = RequestMethod.GET)
    public String edirPlacedOrder(@PathVariable String id,ModelMap model) throws Exception{
        hashSecure = new HashSecure();
        int entity_id = Integer.parseInt(hashSecure.decrypt(id));
        OrderMaster itemOrder = orderService.findById(entity_id);
        OrderItems itemEmpty = new OrderItems();
        List<OrderItems> itemFirstIndex = itemOrder.getOrderItemsList();
        itemFirstIndex.add(0, itemEmpty);
        itemOrder.setOrderItemsList(itemFirstIndex);
        model.addAttribute("pageTitle","Edit Order Placed");
        model.addAttribute("editOrder",itemOrder);
        return "purchase/placeOrder";
    }
}
