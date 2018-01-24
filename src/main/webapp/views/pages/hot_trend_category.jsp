<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="categories" value="${params.categories}" />
<div class="category-tab">
  <!--category-tab-->
  <div class="col-sm-12">
    <ul class="nav nav-tabs">
      <c:forEach var="category" items="${categories}" varStatus="loop">
        <li class="${loop.index == 0 ? 'active' : '' }"><a
          href="#category-${category.getId()}" data-toggle="tab">
            ${category.getName()} </a></li>
      </c:forEach>
    </ul>
  </div>

  <div class="tab-content">
    <c:forEach var="category" items="${categories}" varStatus="loop">
      <div class="tab-pane fade ${loop.index == 0 ? 'active in' : '' }"
        id="category-${category.getId()}">
        <c:forEach var="product" items="${category.getProducts()}">
          <div class="col-sm-3">
            <div class="product-image-wrapper">
              <div class="single-products">
                <div class="productinfo text-center">
                  <img class="product-image"
                    src="${contextPath}/${product.getAvatar()}" alt="" />
                  <h2>$${product.getPrice()}</h2>
                  <p>${product.getName()}</p>
                  <form:form
                    action="${contextPath}/products/${product.getId()}/carts/create"
                    method="POST" modelAttribute="cartInfo">
                    <button class="btn btn-default add-to-cart"
                      type="submit">
                      <i class="fa fa-shopping-cart"></i>Add to cart
                    </button>
                  </form:form>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </c:forEach>
  </div>
</div>
