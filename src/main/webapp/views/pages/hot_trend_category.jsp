<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="category-tab">
  <div class="col-sm-12">
    <ul class="nav nav-tabs">
      <c:forEach var="category" items="${categories}" varStatus="loop">
        <li class="${loop.index == 0 ? 'active' : '' }">
          <a href="#category-${category.getId()}" data-toggle="tab">${category.getName()}</a>
        </li>
      </c:forEach>
    </ul>
  </div>

  <div class="tab-content">
    <c:forEach var="category" items="${categories}" varStatus="loop">
      <div class="tab-pane fade ${loop.index == 0 ? 'active in' : '' }"
        id="category-${category.getId()}">
        <c:forEach var="product" items="${category.getProducts()}">
          <div class="col-sm-4">
            <c:set var="product" value="${product}" scope="session" />
            <c:import url="/views/products/mini_product.jsp" />
            <c:remove var="product" scope="session" />
          </div>
        </c:forEach>
      </div>
    </c:forEach>
  </div>
</div>
