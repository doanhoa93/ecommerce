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
        
        <c:if test="${product.getIsPromotion()}">
          <div class="product-sale">
            <img alt="" src="${contextPath}/assets/images/home/sale.png" class="img-responsive">
          </div>
        </c:if>
        
        <div class="product-stock">
          <span>Status: </span>
          <c:choose>
            <c:when test="${product.getNumber() > 0}">
              <c:out value="Stock" />
            </c:when>
            
            <c:otherwise>
              <c:out value="Out of stock" />
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
