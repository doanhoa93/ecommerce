<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="admin-order">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">
          &times;
        </button>
        <h4 class="modal-title">Order detail</h4>
      </div>
      <div class="modal-body">
        <div class="order-detail-info">
          <div class="order-field">
            <span>Customer: </span>
            <span class="order-detail-customer">${order.getUser().getName()}</span>
          </div>
          
          <div class="order-field">
            <span>Total Price: </span>
            <span class="order-detail-price">
              <fmt:setLocale value="en_US" />
              <fmt:formatNumber value="${order.getTotalPrice()}" type="currency" />
            </span>              
          </div>
          
          <div class="order-field">
            <span>Status: </span>
            <c:set var="status" value="${order.getStatus()}" scope="page" />
            <span class="order-status status-${status}">
              ${statuses[status]}
            </span>              
          </div>          
        </div>
        <table class="table table-bordered order-table" style="width: 100%">
          <thead class="">
            <tr>
              <th>#</th>
              <th>Product's avatar</th>
              <th>Product's name</th>
              <th>Product's price</th>
              <th>Quantity</th>
              <th>Sum price</th>
            </tr>
          </thead>
        
          <tbody>
            <c:forEach items="${orderProducts}" var="orderProduct" varStatus="loop">
              <c:set var="product" value="${orderProduct.getProduct()}" scope="page" />
              <tr>
                <td>${loop.index + 1}</td>
                <td><img src="${product.getAvatar()}" class="img-responsive order-avatar" /></td>
                <td>${product.getName()}</td>
                <td>
                  <fmt:setLocale value="en_US" />
                  <fmt:formatNumber value="${product.getPrice()}" type="currency" />        
                </td>
                <td>${orderProduct.getQuantity()}</td>
                <td>
                  <fmt:setLocale value="en_US" />
                  <fmt:formatNumber value="${product.getPrice() * orderProduct.getQuantity()}" 
                    type="currency" />              
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>      
        <div>
          <c:set var="order" value="${order}" scope="session" />
          <c:set var="statuses" value="${statuses}" scope="session" />
          <c:import url="/views/admin/orders/form.jsp" />
          <c:remove var="order" scope="session"/>
          <c:remove var="statuses" scope="session"/>
        </div>
      </div>
    </div>
  </div>
</div>
