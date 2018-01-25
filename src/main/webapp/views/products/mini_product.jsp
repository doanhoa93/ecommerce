<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
        <form:input path="quantity" />
        <form:button class="btn btn-default add-to-cart" type="submit">
          <i class="fa fa-shopping-cart"></i>Add to cart
        </form:button>
      </form:form>
    </div>
  </div>
</div>
