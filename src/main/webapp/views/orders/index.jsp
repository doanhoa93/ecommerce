<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2 class="center">This is your orders</h2>
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
      <tr>
        <td>${loop.index + 1}</td>
        <td>${order.getProductQuantity()}</td>
        <td>
          <fmt:setLocale value="en_US" />
          <fmt:formatNumber value="${order.getTotalPrice()}" type="currency" />        
        </td>
        <td>${order.getCreatedAt()}</td>
        <c:set var="status" value="${order.getStatus() - 1}"
          scope="page" />
        <td>${statuses[status]}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
