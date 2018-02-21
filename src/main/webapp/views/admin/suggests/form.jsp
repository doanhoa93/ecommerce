<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="suggest-actions" data-id="${suggest.getId()}">
  <c:choose>
    <c:when test="${suggest.getStatus() == 'WAITING'}">
      <button class="btn btn-primary btn-suggest-update" data-status="ACCEPT">Accept</button>
      <button class="btn btn-danger btn-suggest-update" data-status="REJECT">Reject</button>
      <button class="btn btn-warning btn-suggest-update" data-status="CANCEL">Cancel</button>
    </c:when>

    <c:when test="${suggest.getStatus() == 'ACCEPT'}">
      <button class="btn btn-warning btn-suggest-update" data-status="CANCEL">Cancel</button>
    </c:when>

    <c:when test="${suggest.getStatus() == 'REJECT'}">
      <button class="btn btn-warning btn-suggest-update" data-status="WAITING">UnReject</button>
    </c:when>

    <c:otherwise>
      <button class="btn btn-default btn-suggest-update" data-status="WAITING">UnCancel</button>
    </c:otherwise>
  </c:choose>
  <div class="clearfix"></div>
</div>
