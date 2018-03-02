<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
  prefix="tilesx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title><tiles:insertAttribute name="title" ignore="true" /></title>

<!-- Default CSS -->
<tilesx:useAttribute id="stylesheets" name="default-css"
  classname="java.util.List" />
<c:forEach var="css" items="${stylesheets}">
  <link rel="stylesheet" href="<c:url value='${css}'/>" type="text/css"
    media="screen" />
</c:forEach>

<!-- Addition CSS -->
<tilesx:useAttribute id="additionStylesheets" name="css"
  classname="java.util.List" />
<c:forEach var="css" items="${additionStylesheets}">
  <link rel="stylesheet" href="<c:url value='${css}'/>" type="text/css"
    media="screen" />
</c:forEach>
</head>

<c:set var="currentUser"
  value="${request.getSession().getAttribute('currentUser')}"
  scope="application" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"
  scope="application" />

<body>
  <div id="wrapper">
    <c:if test="${flash != null}">
      <div class="flash">
        <div class="alert alert-${flash.type}">${flash.content}</div>
      </div>
    </c:if>
    <tiles:insertAttribute name="header" ignore="true" />

    <div id="page-wrapper" class="admin-body">
      <tiles:insertAttribute name="body" ignore="true" />
      <div id="form-category" class="modal fade" role="dialog"></div>                
    </div>
  </div>

  <!-- Default JS -->
  <tilesx:useAttribute id="javascripts" name="default-js"
    classname="java.util.List" />
  <c:forEach var="js" items="${javascripts}">
    <script src="<c:url value='${js}'/>" type="text/javascript"></script>
  </c:forEach>

  <!-- Addition JS -->
  <tilesx:useAttribute id="additionJavascripts" name="js"
    classname="java.util.List" />
  <c:forEach var="js" items="${additionJavascripts}">
    <script src="<c:url value='${js}'/>" type="text/javascript"></script>
  </c:forEach>
</body>
</html>
