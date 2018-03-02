<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
  <h2 class="center">This is your orders (${ordersSize} orders)</h2>
</div>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-body">
        <table class="table table-striped table-bordered table-hover orders-table"
          id="dataTables-example" style="width: 100%">
          <thead>
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
              <tr class="${loop.index % 2 == 0 ? 'odd' : 'even'} tr-href" 
                data-href="${contextPath}/orders/${order.getId()}">
                <td>${loop.index + 1}</td>
                <td>${order.getProductQuantity()}</td>
                <td>
                  <fmt:setLocale value="en_US" />
                  <fmt:formatNumber value="${order.getTotalPrice()}" type="currency" />
                </td>
                <td>${order.getCreatedAt()}</td>
                <td class="status status-${order.getStatus()}">${order.getStatus()}</td>
              </tr>
            </c:forEach>
          </tbody>          
        </table>
      </div>
    </div>
  </div>
</div>
