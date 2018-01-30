<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="features_items">
  <h2 class="title text-center">Recently Products</h2>
  <c:forEach var="product" items="${recentProducts}">
    <div class="col-sm-4">
      <c:set var="product" value="${product}" scope="session" />
      <c:import url="/views/products/mini_product.jsp" />
      <c:remove var="product" scope="session" />
    </div>
  </c:forEach>
</div>
