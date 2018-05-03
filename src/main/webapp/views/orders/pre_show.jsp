<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal-dialog pre-order">
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal">&times;</button>
      <h4 class="modal-title center">Confirm the order</h4>
    </div>

    <div class="modal-body">
      <div class="order">
        <div class="order-detail-info">
          <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
            <div class="order-field">
              <span>Total Price: </span>
              <span class="order-detail-price">
                <fmt:setLocale value="en_US" />
                <fmt:formatNumber value="" type="currency" />
              </span>
            </div>

            <div class="order-field">
              <span> Information of order </span>
              <ul class="order-detail-info">
                <li>
                  PhoneNumber :
                  <b class="preorder-phone-number">${order.getPhoneNumber()}</b>
                </li>

                <li>
                  Email :
                  <b class="preorder-email">${order.getEmail()}</b>
                </li>

                <li>
                  Name :
                  <b class="preorder-name">${order.getName()}</b>
                </li>

                <li>
                  Address :
                  <b class="preorder-address">${order.getAddress()}</b>
                </li>
              </ul>
            </div>
          </div>

          <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
            <div class="order-field">
              <span>Status: </span>
              <span class="order-status status-WAITING">WAITING</span>
            </div>
          </div>
          <div class="clearfix"></div>
        </div>

        <div class="order-table">
          <table class="table table-bordered order-table" style="width: 100%">
            <thead class="">
              <tr>
                <th>#</th>
                <th>Product's avatar</th>
                <th>Product's name</th>
                <th>Product's number</th>
                <th>Quantity</th>
                <th>Product's price</th>
                <th>Sum price</th>
                <th>Status</th>
              </tr>
            </thead>

            <tbody>
              <c:forEach items="${carts}" var="cart" varStatus="loop">
                <c:set var="product" value="${cart.getProduct()}" scope="page" />
                <tr>
                  <td>${loop.index + 1}</td>
                  <td>
                    <a href="${contextPath}/products/${product.getId()}">
                      <img src="${product.getAvatar()}" class="img-responsive order-avatar" />
                    </a>
                  </td>
                  <td>
                    <a href="${contextPath}/products/${product.getId()}">${product.getName()}</a>
                  </td>
                  <td>${product.getNumber()}</td>
                  <td>${cart.getQuantity()}</td>
                  <td>
                    <fmt:setLocale value="en_US" />
                    <fmt:formatNumber value="${product.getPrice()}" type="currency" />
                  </td>
                  <td>
                    <fmt:setLocale value="en_US" />
                    <fmt:formatNumber value="${product.getPrice() * cart.getQuantity()}"
                      type="currency" />
                  </td>
                  <td class="order-product-status">
                    <strong class="order-product-status status-WAITING"> WAITING</strong>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>

        <div class="actions">
          <input type="submit" data-confirm="Are you sure?"
            class="btn btn-primary btn-order btn-order-submit" value="Save" />
          <a href="${contextPath}/carts" class="btn btn-default btn-cancel-order">Cancel</a>
        </div>
        <div class="clearfix"></div>
      </div>
    </div>
  </div>
</div>
