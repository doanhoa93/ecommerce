<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="notifications">
  <c:forEach var="notification" items="${notifications}">
    <div class="notification ${notification.isWatched() ? '' : 'unwatched'}"
      data-id="${notification.getId()}">
      <div class="sub-item-icon">
        <img src="${contextPath}/assets/images/default/order.png"
          class="img-resposive sub-item-order-icon" />
      </div>

      <div class="sub-item-content">
        <a class="notification-href" href="${contextPath}/orders/${notification.getOrder().getId()}">
          <span class="notification-content">${notification.getContent()}</span>
        </a>
        <div class="small notification-time">${notification.getCreatedAt()}</div>
      </div>

      <div class="clearfix"></div>
    </div>
  </c:forEach>
</div>

<div class="notifications-paginate">
  <c:set var="paginate" value="${paginate}" scope="session" />
  <c:import url="/views/shared/paginate.jsp" />
  <c:remove var="paginate" scope="session" />
</div>
