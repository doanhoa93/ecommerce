<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
  <c:when test="${orders.size() > 0}">
    <h2 class="center">This is your orders (${ordersSize} orders)</h2>
    <table class="table table-bordered orders-table">
      <thead class="">
        <tr>
          <th>#</th>
          <th>Product number</th>
          <th>Total price</th>
          <th>Created at</th>
          <th>Status</th>
        </tr>
      </thead>

      <tbody>
        <c:forEach items="${orders}" var="order" varStatus="loop">
          <tr class="order-tr" data-href="/orders/${order.getId()}">
            <td>${loop.index + 1}</td>
            <td>${order.getProductQuantity()}</td>
            <td>
              <fmt:setLocale value="en_US" />
              <fmt:formatNumber value="${order.getTotalPrice()}" type="currency" />
            </td>
            <td>${order.getCreatedAt()}</td>
            <c:set var="status" value="${order.getStatus()}"
              scope="page" />
            <td>${statuses[status]}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <div class="orders-paginate">
      <c:set var="paginate" value="${paginate}" scope="session" />
      <c:import url="/views/shared/paginate.jsp" />
      <c:remove var="paginate" scope="session" />
      <c:remove var="paginateJS" scope="session" />
    </div>
  </c:when>

  <c:otherwise>
    <h3>No orders</h3>
  </c:otherwise>
</c:choose>
