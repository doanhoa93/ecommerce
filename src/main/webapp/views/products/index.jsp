<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="product" items="${products}">
  <div class="col-sm-4">
    <c:set var="product" value="${product}" scope="session" />
    <c:import url="/views/products/mini_product.jsp" />
    <c:remove var="product" scope="session" />
  </div>
</c:forEach>

<div class="products-paginate">
  <c:set var="paginate" value="${paginate}" scope="session" />
  <c:set var="paginateJS" value="true" scope="session" />
  <c:import url="/views/shared/paginate.jsp" />
  <c:remove var="paginate" scope="session" />
  <c:remove var="paginateJS" scope="session" />
</div>
