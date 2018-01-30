<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="product" items="${products}">
  <div class="col-sm-4">
    <c:set var="product" value="${product}" scope="session" />
    <c:import url="/views/products/mini_product.jsp" />
    <c:remove var="product" scope="session" />
  </div>
</c:forEach>
