<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
  <c:when test="${products.size() > 0}">
    <c:forEach var="product" items="${products}">
      <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6">
        <c:set var="product" value="${product}" scope="session" />
        <c:import url="/views/products/description_product.jsp" />
        <c:remove var="product" scope="session" />
      </div>
    </c:forEach>

    <div class="products-paginate">
      <c:set var="paginate" value="${paginate}" scope="session" />
      <c:import url="/views/shared/paginate.jsp" />
      <c:remove var="paginate" scope="session" />
    </div>
  </c:when>

  <c:otherwise>
    <h2>No products</h2>
  </c:otherwise>
</c:choose>
