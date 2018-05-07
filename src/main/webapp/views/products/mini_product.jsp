<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="product-image-wrapper">
  <div class="single-products">
    <div class="productinfo text-center">
      <div class="product-avatar">
        <a href="${contextPath}/products/${product.getId()}">
          <img class="product-image" src="${product.getAvatar()}" alt="" />
        </a>

        <c:if test="${product.getPromotion() != null}">
          <div class="product-sale">
            <img alt="" src="${contextPath}/assets/images/sample/sale.png" class="img-responsive">
          </div>
        </c:if>

        <div class="product-stock">
          <span>Status: </span>
          <c:choose>
            <c:when test="${product.getNumber() > 0}">
              <span class="stock">Stock</span>
            </c:when>

            <c:otherwise>
              <span class="out-stock">Out of stock</span>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
      <h2>
        <fmt:setLocale value="en_US" />
        <fmt:formatNumber value="${product.getPrice()}" type="currency" />
      </h2>
      <p>
        <a href="${contextPath}/products/${product.getId()}" class="product-name">
          ${product.getName()} </a>
      </p>
      <form:form id="add-cart" action="${contextPath}/carts" method="POST" modelAttribute="cart">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="hidden" name="productId" value="${product.getId()}" />
        <button class="btn btn-default add-to-cart" type="submit">
          <i class="fa fa-shopping-cart"></i>
          Add to cart
        </button>
      </form:form>
    </div>
  </div>
</div>
