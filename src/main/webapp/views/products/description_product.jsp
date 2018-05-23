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
            <img alt="" src="${contextPath}/assets/images/default/sale.png" class="img-responsive">
          </div>
        </c:if>

        <div class="product-info">
          <p>${product.getInformation()}</p>
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
    </div>
  </div>
</div>
