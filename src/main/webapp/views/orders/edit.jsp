<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="order">
  <h3>
    Your order (
    <span class="order-size">${orderProducts.size()}</span>
    products)
  </h3>
  <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 alert error">
    <div>${errors.order}</div>
    <div>${errors.orderProducts}</div>
  </div>
  <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
    <form:form id="edit-order" action="${contextPath}/orders/${orderInfo.getId()}" method="PATCH"
      enctype="multipart/form-data" modelAttribute="orderInfo" class="order-form">
      <input type="hidden" name="sessionId" value="${token}" />
      <form:hidden path="status" />
      <div class="field">
        <label>Phone Number(*):</label>
        <form:input path="phoneNumber" placeholder="Your phone number" class="form-control"
          value="${orderInfo.getPhoneNumber()}" />
        <span class="error">${errors.phoneNumber}</span>
      </div>

      <div class="field">
        <label>Name(*):</label>
        <form:input path="name" placeholder="Your name" class="form-control"
          value="${orderInfo.getName()}" />
        <span class="error">${errors.name}</span>          
      </div>

      <div class="field">
        <label>Email(*):</label>
        <form:input path="email" placeholder="Your email" class="form-control"
          value="${orderInfo.getEmail()}" />
        <span class="error">${errors.email}</span>          
      </div>

      <div class="field">
        <label>Address:</label>
        <form:input path="address" placeholder="Your address" class="form-control"
          value="${orderInfo.getAddress()}" />
        <span class="error">${errors.address}</span>          
      </div>
    </form:form>

    <div class="order-left">
      <c:forEach var="orderProduct" items="${orderProducts}">
        <c:set var="product" value="${orderProduct.getProduct()}" scope="page" />

        <div class="order-product order-product-${orderProduct.getId()}"
          data-id="${orderProduct.getId()}" data-product-id="${product.getId()}">
          <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 order-product-left">
            <div class="order-product-image-panel">
              <img src="${product.getAvatar()}" class="img-responsive order-product-image">
            </div>

            <div class="order-product-info">
              <div class="product-name">
                <a href="${contextPath}/products/${product.getId()}">
                  <b>${product.getName()}</b>
                </a>
              </div>
              <div>${product.getInformation()}</div>
            </div>
            <div class="clearfix"></div>
          </div>

          <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 order-product-right">
            <div class="order-product-price order-product-price-${orderProduct.getId()}">
              <fmt:setLocale value="en_US" />
              <fmt:formatNumber value="${product.getPrice()}" type="currency" />
            </div>

            <div class="order-product-quantity-form" data-id="${orderProduct.getId()}">
              <span class="quantity-minus">
                <i>-</i>
              </span>
              <input class="order-product-quantity" type="text" readonly="readonly"
                value="${orderProduct.getQuantity()}" placeholder="0">
              <span class="quantity-plus">
                <i>+</i>
              </span>
            </div>

            <div class="order-product-number">
              Number of product:
              <b class="product-number"> ${orderProduct.getProduct().getNumber()} </b>
            </div>
            <div class="clearfix"></div>
          </div>

          <div class="order-product-close" data-id="${orderProduct.getId()}">
            <i class="fa fa-times" aria-hidden="true"></i>
          </div>
          <div class="clearfix"></div>
        </div>
      </c:forEach>
    </div>
  </div>

  <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 order-right">
    <div class="total-money">
      <b>Total money:</b>
      <span class="total-money-value">
        <fmt:setLocale value="en_US" />
        <fmt:formatNumber value="" type="currency" />
      </span>
    </div>

    <div class="order-form">
      <button class="btn btn-primary btn-update-order" data-id="${orderInfo.getId()}">Save</button>
    </div>
  </div>
  <div class="clearfix"></div>
</div>
