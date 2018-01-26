<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h3>
  Your cart (<span class="carts-size">${params.carts.size()}</span>
  products)
</h3>
<div class="carts">
  <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 alert alert-warning"></div>
  <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 carts-left">
    <c:forEach var="cart" items="${params.carts}">
      <c:set var="product" value="${cart.getProduct()}" scope="page" />

      <div class="cart-product cart-product-${cart.getId()}"
        data-id="${cart.getId()}">
        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 cart-left">
          <div class="cart-product-image-panel">
            <img src="${product.getAvatar()}"
              class="img-responsive cart-product-image">
          </div>

          <div class="cart-product-info">
            <div class="cart-product-name">
              <b>${product.getName()}</b>
            </div>
            <div>${product.getInformation()}</div>
          </div>
          <div class="clearfix"></div>
        </div>

        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 cart-right">
          <div class="cart-product-price">
            <fmt:setLocale value="en_US" />
            <fmt:formatNumber value="${product.getPrice()}"
              type="currency" />
          </div>

          <div class="cart-quantity-form" data-id="${cart.getId()}">
            <span class="quantity-minus"> <i>-</i>
            </span> <input class="cart-quantity" type="text"
              readonly="readonly" value="${cart.getQuantity()}"
              placeholder="0"> <span class="quantity-plus">
              <i>+</i>
            </span>
          </div>
          <div class="clearfix"></div>
        </div>

        <div class="cart-close" data-id="${cart.getId()}">
          <i class="fa fa-times" aria-hidden="true"></i>
        </div>

        <div class="cart-select">
          <label> <input type="checkbox" name="selectCart[]"
            value="${cart.getId()}" class="cart-product-select">
            Select?
          </label>
        </div>
        <div class="clearfix"></div>
      </div>

    </c:forEach>
  </div>

  <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 carts-right">
    <div class="total-money">
      <b>Total money:</b> <span class="total-money-value"> <fmt:setLocale
          value="en_US" /> <fmt:formatNumber value="" type="currency" />
      </span>
    </div>

    <div class="order-form">
      <button class="btn btn-primary btn-order">Order</button>
    </div>
  </div>
  <div class="clearfix"></div>
</div>
