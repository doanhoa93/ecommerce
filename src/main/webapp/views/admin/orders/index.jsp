<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Orders</h1>
  </div>
</div>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-body">
        <table class="table table-striped table-bordered table-hover admin-order-table"
          id="dataTables-example" style="width: 100%">
          <thead>
            <tr>
              <th>#</th>
              <th>Customer</th>
              <th>Product number</th>
              <th>Total price</th>
              <th>Created at</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${orders}" var="order" varStatus="loop">
              <tr class="${loop.index % 2 == 0 ? 'odd' : 'even'} tr-href-js order-${order.getId()}"
                data-href="${contextPath}/admin/orders/${order.getId()}">
                <td>${loop.index + 1}</td>
                <td>${order.getUser() != null ? order.getUser().getName() : 'Guest'}</td>
                <td>${order.getOrderProducts().size()}</td>
                <td>
                  <fmt:setLocale value="en_US" />
                  <fmt:formatNumber value="${order.getTotalPrice() + loop.index}" type="currency" />
                </td>
                <td>${order.getCreatedAt()}</td>
                <td class="center order-status status-${order.getStatus()}">
                  ${order.getStatus()}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <div id="form-order" class="modal fade" role="dialog"></div>
      </div>
    </div>
  </div>
</div>
