<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:forEach var="orderProduct" items="${orderProducts}" varStatus="loop">
  <c:set var="product" value="${orderProduct.getProduct()}" />

  <div class="order-product">
    <div class="col-lg-1 col-md-1">
      <label>#${loop.index + 1}</label>
    </div>
    <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12 order-left">
      <div class="order-product-image-panel">
        <img src="${product.getAvatar()}"
          class="img-responsive order-product-image">
      </div>

      <div class="order-product-info">
        <div class="order-product-name">
          <a href="${contextPath}/products/${product.getId()}">
            <b>${product.getName()}</b>
          </a>
        </div>
        <div>${product.getInformation()}</div>
      </div>
      <div class="clearfix"></div>
    </div>

    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 order-right">
      <div class="order-product-price">
        <fmt:setLocale value="en_US" />
        <fmt:formatNumber value="${product.getPrice()}"
          type="currency" />
      </div>

      <div class="order-quantity-form">
        <span>Quantity: <label>${orderProduct.getQuantity()}</label></span>
      </div>

      <div class="total-money">
        <span>Total:
          <label>
            <fmt:setLocale value="en_US" />
            <fmt:formatNumber value="${orderProduct.getQuantity() * product.getPrice()}"
              type="currency" />
          </label>
       </span>
      </div>
      <div class="clearfix"></div>
    </div>
    <div class="clearfix"></div>
  </div>
</c:forEach>
