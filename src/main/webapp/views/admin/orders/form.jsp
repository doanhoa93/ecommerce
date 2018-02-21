<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="order-actions" data-id="${order.getId()}">
  <c:choose>
    <c:when test="${order.getStatus() == 'WAITING'}">
      <button class="btn btn-primary btn-order-update" data-status="ACCEPT">Accept</button>
      <button class="btn btn-danger btn-order-update" data-status="REJECT">Reject</button>
      <button class="btn btn-warning btn-order-update" data-status="CANCEL">Cancel</button>
    </c:when>

    <c:when test="${order.getStatus() == 'ACCEPT'}">
      <button class="btn btn-warning btn-order-update" data-status="CANCEL">Cancel</button>
    </c:when>

    <c:when test="${order.getStatus() == 'REJECT'}">
      <button class="btn btn-warning btn-order-update" data-status="WAITING">UnReject</button>
    </c:when>

    <c:otherwise>
      <button class="btn btn-default btn-order-update" data-status="WAITING">UnCancel</button>
    </c:otherwise>
  </c:choose>
  <div class="clearfix"></div>
</div>
