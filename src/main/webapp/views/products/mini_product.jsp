<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="product-image-wrapper">
  <div class="single-products">
    <div class="productinfo text-center">
      <a href="${contextPath}/products/${product.getId()}">
        <img class="product-image" src="${contextPath}/${product.getAvatar()}" alt="" />
      </a>
      <h2>            
        <fmt:setLocale value="en_US" />
        <fmt:formatNumber value="${product.getPrice()}" type="currency" />
      </h2>
      <p>
        <a href="${contextPath}/products/${product.getId()}" class="product-name">
          ${product.getName()}
        </a>
      </p>
      <form:form
        action="${contextPath}/products/${product.getId()}/carts"
        method="POST" modelAttribute="cartInfo">
        <button class="btn btn-default add-to-cart"
          type="submit">
          <i class="fa fa-shopping-cart"></i>Add to cart
        </button>
      </form:form>
    </div>
  </div>
</div>
