<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="uri" value="${request.getRequestURI()}${param.size() == 0 ? '' : '?'}" />
<c:forEach var="pageParameter" items="${param}">
  <c:if test="${pageParameter.key != 'page' && pageParameter.key != 'objectType'}">
    <c:set var="uri" value="${uri}&${pageParameter.key}=${pageParameter.value}" />
  </c:if>
</c:forEach>
<c:set var="isProduct" value="${param.objectType == 'product'}" />

<ul class="nav nav-tabs">
  <li class="${isProduct ? 'active' : null}">
    <a href="${uri}&objectType=product">Products</a>
  </li>

  <li class="${!isProduct ? 'active' : null}">
    <a href="${uri}&objectType=category">Categories</a>
  </li>
</ul>

<div class="tab-content">
  <div id="products" class="tab-pane fade ${isProduct ? 'in active' : null}">
    <c:import url="/views/searches/products.jsp" />
  </div>

  <div id="categories" class="tab-pane fade ${!isProduct ? 'in active' : null}">
    <c:import url="/views/searches/categories.jsp" />
  </div>
</div>
