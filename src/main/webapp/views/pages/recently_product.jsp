<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="features_items">
  <!--features_items-->
  <h2 class="title text-center">Recently Products</h2>
  <c:forEach var="product"
    items="${params.recentProducts}">
    <div class="col-sm-4">
      <c:import url="/views/products/mini_product.jsp" />
      <c:set var="product" value="${product}" scope="session" />
    </div>
  </c:forEach>
</div>
<!--features_items-->