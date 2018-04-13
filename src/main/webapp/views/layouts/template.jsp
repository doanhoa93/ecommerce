<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<!-- Meta -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<!-- Default CSS -->
<tilesx:useAttribute id="stylesheets" name="default-css" classname="java.util.List" />
<c:forEach var="css" items="${stylesheets}">
  <link rel="stylesheet" href="<c:url value='${css}'/>" type="text/css" media="screen" />
</c:forEach>

<!-- Addition CSS -->
<tilesx:useAttribute id="additionStylesheets" name="css" classname="java.util.List" />
<c:forEach var="css" items="${additionStylesheets}">
  <link rel="stylesheet" href="<c:url value='${css}'/>" type="text/css" media="screen" />
</c:forEach>

<c:set var="currentUser" value="${request.getAttribute('currentUser')}" scope="application" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application" />

<c:set var="cartSizeSession"
  value="${request.getAttribute('cartSize') ? request.getAttribute('cartSize') : 0}" />
<c:set var="orderSizeSession"
  value="${request.getAttribute('orderSize') ? request.getAttribute('orderSize') : 0}" />

<c:set var="cartSize"
  value="${currentUser != null ? currentUser.getCarts().size() : cartSizeSession}"
  scope="application" />
<c:set var="orderSize"
  value="${currentUser != null ? currentUser.getOrders().size() : orderSizeSession}"
  scope="application" />
<body>
  <div class="header">
    <c:if test="${flash != null}">
      <div class="flash">
        <div class="alert alert-${flash.type}">${flash.content}</div>
      </div>
    </c:if>
    <tiles:insertAttribute name="header" ignore="true" />
  </div>

  <div class="body">
    <div class="container container-body">
      <tiles:insertAttribute name="body" ignore="true" />
    </div>

    <div id="add-cart-modal" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <p class="add-cart-result-content success">Add product to cart is successful</p>
            <p class="add-cart-result-content fail">Add product to cart is failure!</p>
          </div>
          <div class="modal-footer">
            <a href="${contextPath}/carts" class="btn btn-default btn-cart">Go to your carts</a>
            <button type="button" class="btn btn-default btn-cart" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="footer">
    <tiles:insertAttribute name="footer" ignore="true" />
  </div>
  <div id="fb-root"></div>
  <input type="hidden" value="${currentUser.getToken()}" class="token" />

  <!-- Default JS -->
  <tilesx:useAttribute id="javascripts" name="default-js" classname="java.util.List" />
  <c:forEach var="js" items="${javascripts}">
    <script src="<c:url value='${js}'/>" type="text/javascript"></script>
  </c:forEach>

  <!-- Addition JS -->
  <tilesx:useAttribute id="additionJavascripts" name="js" classname="java.util.List" />
  <c:forEach var="js" items="${additionJavascripts}">
    <script src="<c:url value='${js}'/>" type="text/javascript"></script>
  </c:forEach>
</head>
</body>
</html>
