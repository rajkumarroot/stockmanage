<%-- 
    Document   : template
    Created on : 18 Dec, 2015, 9:16:14 PM
    Author     : node
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="tiles1" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="titleKey"> 
    <tiles:getAsString name="title" />
</c:set>
<html>
    <head>
        <title><tiles1:getAsString name="title"/></title>
        <tiles1:insertAttribute name="head"/>
        <meta charset="utf-8">
    </head>
    <body class="hold-transition skin-blue sidebar-mini" ng-app="billing">
        <div class="wrapper">
            <header class="main-header">
                <tiles1:insertAttribute name="header" /> 
            </header>
            <aside class="main-sidebar">
                <tiles1:insertAttribute name="sidebar" />
            </aside>
            <div class="content-wrapper">
                <tiles1:insertAttribute name="body" /> 
            </div>
            
        </div>
    </body>
</html>
