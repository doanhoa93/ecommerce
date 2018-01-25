<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="features_items">
  <!--features_items-->
  <h2 class="title text-center">Recently Products</h2>
  <c:forEach var="product" items="${params.recentProducts}">
    <div class="col-sm-4">
      <div class="product-image-wrapper">
        <div class="single-products">
          <div class="productinfo text-center">
            <img class="product-image"
              src="${contextPath}/${product.getAvatar()}"
              alt="" />
            <h2>$${product.getPrice()}</h2>
            <p>${product.getName()}</p>
            <form:form
              action="${contextPath}/products/${product.getId()}/carts/create"
              method="POST" modelAttribute="cartInfo">
              <button class="btn btn-default add-to-cart" type="submit">
                <i class="fa fa-shopping-cart"></i>Add to cart
              </button>
            </form:form>
          </div>
        </div>
      </div>
    </div>
  </c:forEach>
</div>
