<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="carts">
  <c:choose>
    <c:when test="${carts.size() > 0}">
      <h3>
        Your cart (
        <span class="carts-size">${cartsSize}</span>
        products)
      </h3>
      <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 alert alert-warning"></div>
      <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 carts-left">
        <div class="cart-products">
          <div class="carts-index">
            <c:set var="carts" value="${carts}" scope="session" />
            <c:import url="/views/carts/index_partial.jsp" />
            <c:remove var="carts" scope="session" />
          </div>
          <div class="carts-load-more">
            <input type="hidden" class="cart-page" value="2" />
            <a class="load-more" data-href="${request.getRequestURI()}?page=">Load more</a>
          </div>
        </div>

        <div class="order-form-create">
          <c:import url="/views/orders/form.jsp" />
        </div>
      </div>
      <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 carts-right">
        <div class="total-money">
          <b>Total money:</b>
          <span class="total-money-value">
            <fmt:setLocale value="en_US" />
            <fmt:formatNumber value="" type="currency" />
          </span>
        </div>
        <div class="order-button">
          <button class="btn btn-primary">Order</button>
        </div>
      </div>

      <div class="clearfix"></div>
    </c:when>
    <c:otherwise>
      <h3>No carts</h3>
    </c:otherwise>
  </c:choose>
</div>
