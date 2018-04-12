<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:forEach var="cart" items="${carts}">
  <c:set var="product" value="${cart.getProduct()}" scope="page" />
  <div class="cart-product cart-product-${cart.getId()}" data-id="${cart.getId()}">
    <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 cart-left">
      <div class="cart-product-image-panel">
        <a href="${contextPath}/products/${product.getId()}">
          <img src="${product.getAvatar()}" class="img-responsive cart-product-image">
        </a>
      </div>
      <div class="cart-product-info">
        <div class="cart-product-name">
          <a href="${contextPath}/products/${product.getId()}">
            <b>${product.getName()}</b>
          </a>
        </div>
        <div>${product.getInformation()}</div>
      </div>
      <div class="clearfix"></div>
    </div>
    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 cart-right">
      <div class="cart-product-price cart-product-price-${cart.getId()}">
        <fmt:setLocale value="en_US" />
        <fmt:formatNumber value="${product.getPrice()}" type="currency" />
      </div>
      <div class="cart-quantity-form" data-id="${cart.getId()}">
        <span class="quantity-minus">
          <i>-</i>
        </span>
        <input class="cart-quantity" type="text" readonly="readonly" value="${cart.getQuantity()}"
          placeholder="0">
        <span class="quantity-plus">
          <i>+</i>
        </span>
      </div>
      <div class="cart-product-number">
        Number of product:
        <b class="product-number"> ${cart.getProduct().getNumber()} </b>
      </div>
      <div class="clearfix"></div>
    </div>
    <div class="cart-close" data-id="${cart.getId()}">
      <i class="fa fa-times" aria-hidden="true"></i>
    </div>
    <div class="cart-select">
      <label>
        <input type="checkbox" name="selectCart[]" value="${cart.getId()}"
          class="cart-product-select cart-product-select-${cart.getId()}">
        Select?
      </label>
    </div>
    <div class="clearfix"></div>
  </div>
</c:forEach>
