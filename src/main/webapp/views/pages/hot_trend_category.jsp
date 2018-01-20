<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="category-tab">
  <!--category-tab-->
  <div class="col-sm-12">
    <ul class="nav nav-tabs">
      <c:forEach var="index" begin="0"
        end="${params.categories.size() - 1}">
        <li class="${index == 0 ? 'active' : '' }"><a
          href="#category-${params.categories.get(index).getId()}"
          data-toggle="tab">
            ${params.categories.get(index).getName()} </a></li>
      </c:forEach>
    </ul>
  </div>

  <div class="tab-content">
    <c:forEach var="index" begin="0"
      end="${params.categories.size() - 1}">
      <div class="tab-pane fade ${index == 0 ? 'active in' : '' }"
        id="category-${params.categories.get(index).getId()}">
        <c:forEach var="product"
          items="${params.categories.get(index).getProducts()}">
          <div class="col-sm-3">
            <c:import url="/views/products/mini_product.jsp" />
            <c:set var="product" value="${product}" scope="session" />
          </div>
        </c:forEach>
      </div>
    </c:forEach>
  </div>
</div>
