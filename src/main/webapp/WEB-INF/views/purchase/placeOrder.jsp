<%-- 
    Document   : placeOrder
    Created on : 3 Mar, 2016, 11:17:25 PM
    Author     : Rajkumar
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="role" value="${fn:toLowerCase(udetail.roleId.roleName)}"/>
<div class="col-lg-12 body-padding">
    <form:form method="post" action="${contextPath}/${role}/inventory/placeorder/postOrder" id="purchaseOrderForm" modelAttribute="defaultorder" commandName="editOrder" cssClass="form-horizontal">
        <div class="form-group">
            <div class="col-lg-12">
                <div class="row">
                <div class="col-lg-5">
                    <div class="form-group">
                        <div class="col-lg-5">
                            <label> Order Date: </label>
                        </div>
                        <div class="col-lg-7">
                            <jsp:useBean id="now" class="java.util.Date"/>    
                            <fmt:formatDate value="${now}" dateStyle="long"/>
                            <form:input path="orderPlacedBy.id" style="width:80px;display:none" tabindex="-1"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-5">
                            <form:label path="vendorId.vendorId" cssClass="control-label ">Supplier: :</form:label>
                            <label>Supplier: </label>
                        </div>
                        <div class="col-lg-7">
                            <form:select path="vendorId.vendorId" id="supplier" cssClass="form-control col-lg-3 input" tabindex="1">
                                <form:option value="0" label="--Select Supplier--" />
                                <form:options items="${suppliers}"/>
                            </form:select>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
        <!-- Product to order -->
        <div class="col-lg-12">
            <table class="table table-bordered table-striped" id="productSelected">
                <tbody>
                    <tr>
                        <td>
                            <form:select path="orderItemsList[0].productId.productId" id="prodCode" cssClass="form-control col-lg-3 input" tabindex="2">
                                <form:option value="0" label="--Select Code--" />
                                <form:options items="${productsListCode}"/>
                            </form:select>
                        </td>
                        <td style="width:80px">
                        <form:input path="orderItemsList[0].weight" style="width:80px" tabindex="3" id="prodWeight"/>
                        </td>
                        <td width="80px">
                            <form:select path="orderItemsList[0].weightIn" style="width:100px"  id="prodUnit" cssClass="form-control col-lg-3 input" tabindex="4">
                                <form:option value="0" label="--Units--" />
                                <form:options items="${weightUnit}"/>
                            </form:select>
                        </td>
                        <td width="80px">
                        <form:input path="orderItemsList[0].qty" style="width:50px" tabindex="5" id="prodQty"/>
                        </td>
                        <td width="50px"><button type="button" tabindex="6" class="add_row"> *</button></td>
                    </tr> 
                    
                </tbody>
            </table>
        </div>
        <!-- end -->
        <div class="col-lg-12">
            <table class="table table-bordered table-striped" id="entryOrder">
                <thead>
                    <tr class="success">
                        <th>Name</th>
                        <th>Weight</th>
                        <th>Weight In</th>
                        <th>Qty</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${(editOrder.orderItemsListSave != null) &&  (!empty editOrder.orderItemsListSave)}">
                        <c:forEach var="itemList" items="${editOrder.orderItemsListSave}" varStatus="itemCount">
                            <tr>
                                <td>
                                    <form:select path="orderItemsList[${itemCount.index+1}].productId.productId" value="${itemList.productId.name}" id="prodCode" cssClass="form-control col-lg-3 input" tabindex="2">
                                        <form:option value="0" label="--Select Code--" />
                                        <form:options items="${productsListCode}"/>
                                    </form:select>
                                </td>
                                <td width="80px"><form:input path="orderItemsList[${itemCount.index+1}].weight" style="width:80px" value="${itemList.weight}" tabindex="3" id="prodWeight"/></td>
                                <td width="80px">
                                    <form:select path="orderItemsList[${itemCount.index+1}].weightIn" style="width:100px" value="${weightUnit[itemList.weightIn]}"  id="prodUnit" cssClass="form-control col-lg-3 input" tabindex="4">
                                        <form:option value="0" label="--Units--" />
                                        <form:options items="${weightUnit}"/>
                                    </form:select>
                                </td>
                                <td width="80px"><form:input path="orderItemsList[${itemCount.index+1}].qty" style="width:50px" value="${itemList.qty}" tabindex="5" id="prodQty"/></td>
                                <td width="50px"><a href="javascript:void(0);"><span class="glyphicon glyphicon-pencil" style="cursor: pointer"></span></a></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
        <div class="col-lg-12">
            <div class="row-fluid pull-right">
                <div class="col-lg-7">
                    <div class="form-group">
                        <div class="col-lg-4">
                            <label>Subtotal</label>
                        </div>
                        <div class="col-lg-8">
                            <label>0.00</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-12 body-padding">
            <div class="row-fluid " style="text-align: center">
                <a href="${contextPath}/${role}/inventory/placeorder" class="btn btn-primary" id="newUser" >Cancel</a>
                <button class="btn btn-primary" type="submit">Place Order</button>
            </div>
        </div>
    </form:form>
</div>
<script>
    
    $(document).ready(function(){
        var i = 1
        $('#supplier').focus();
        $("select[id^='prodCode']").change(function(){
            id = $(this).attr('id');
            var lastChar = id.substr(id.length - 1);
            //alert(lastChar);
            $("#prodName"+lastChar).val($(this).val());
        });
        $('.add_row').click(function(){
            var cloned = $("#productSelected tbody tr:last").clone(true);
            cloned.find("td:last").empty();
            cloned.find("select").each(function() {
                $(this).attr({
                  'id': function(_, id) { return id +i },
                  'name': function(_, name) { 
                      return name.replace("[0]","["+i+"]"); 
                  },
                  'tabindex':'-1',
                  'value':''
                });
              });
              cloned.find("input").each(function() {
                $(this).attr({
                  'id': function(_, id) { return id +i },
                  'name': function(_, name) { return name.replace("[0]","["+i+"]") },
                  'tabindex':'-1',
                  'value':''
                });
              });
              $("#productSelected tbody tr:last").find("select").each(function() {
                cloned.find('select[id=' + this.id+i + ']').val(this.value);
              });
              $("#productSelected tbody tr:last").find("input").each(function() {
                cloned.find(':input[id=' + this.id+i + ']').val(this.value);
              });
              $("#entryOrder tbody").append(cloned);
              i++;
              $("#productSelected tbody tr:last").find("select").each(function() {
                $(this).val(0);
              });
              $("#productSelected tbody tr:last").find("input").each(function() {
                $(this).val(0);
              });
              $("select[id^='prodCode']").change(function(){
                    id = $(this).attr('id');
                    var lastChar = id.substr(id.length - 1);
                    $("#prodName"+lastChar).val($(this).val());
                });
        })
    });
</script>