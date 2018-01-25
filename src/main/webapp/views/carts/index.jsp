<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>Your cart (${params.carts.size()} products)</h3>
<div class="carts">
  <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 carts-left">
    <c:forEach var="cart" items="${params.carts}">
      <c:set var="product" value="${cart.getProduct()}" scope="page" />

      <div class="cart-product cart-product-${product.getId()}">
        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 cart-left">
          <div class="cart-product-image-panel">
            <img src="${product.getAvatar()}"
              class="img-responsive cart-product-image">
          </div>

          <div class="product-info">
            <div class="product-name">
              <b>${product.getName()}</b>
            </div>
            <div>${product.getInformation()}</div>
          </div>
          <div class="clearfix"></div>
        </div>

        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 cart-right">
          <div class="product-price">$${product.getPrice()}</div>

          <div class="cart-quantity-form">
            <span class="quantity-minus"> <i>-</i>
            </span> <input class="cart-quantity" type="text"
              readonly="readonly" value="${cart.getQuantity()}"
              placeholder="0"> <span class="quantity-plus">
              <i>+</i>
            </span>
          </div>
          <div class="clearfix"></div>
        </div>

        <div class="cart-close">
          <i class="fa fa-times" aria-hidden="true"></i>
        </div>

        <div class="cart-select">
          <label> <input type="checkbox" name="selectCart"
            value="${cart.getId()}" class="product-select">
            Select?
          </label>
        </div>
        <div class="clearfix"></div>
      </div>

    </c:forEach>
  </div>

  <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 carts-right">
    <div class="total-money">
      <b>Total money:</b> $<span class="total-money-value">100</span>
    </div>
    
    <div class="order-form">
      <button class="btn btn-primary btn-order">
        Order
      </button>
    </div>
  </div>
  <div class="clearfix"></div>
</div>
