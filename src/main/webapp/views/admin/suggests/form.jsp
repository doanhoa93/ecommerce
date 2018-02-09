<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="suggest-actions" data-id="${suggest.getId()}">
  <c:set var="status" value="${suggest.getStatus()}"></c:set>
  <c:choose>
    <c:when test="${statuses[status] == statuses[0]}">
      <button class="btn btn-primary btn-suggest-update" data-status="1">Accept</button>
      <button class="btn btn-danger btn-suggest-update" data-status="2">Reject</button>
      <button class="btn btn-warning btn-suggest-update" data-status="3">Cancel</button>
    </c:when>

    <c:when test="${statuses[status] == statuses[1]}">
      <button class="btn btn-warning btn-suggest-update" data-status="3">Cancel</button>
    </c:when>

    <c:when test="${statuses[status] == statuses[2]}">
      <button class="btn btn-warning btn-suggest-update" data-status="0">UnReject</button>
    </c:when>

    <c:otherwise>
      <button class="btn btn-default btn-suggest-update" data-status="0">UnCancel</button>
    </c:otherwise>
  </c:choose>
  <div class="clearfix"></div>
</div>
