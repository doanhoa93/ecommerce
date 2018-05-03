<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="new-order">
  <div class="error cart-error">
    <div>${errors.cartIds}</div>
  </div>

  <form:form id="new-order" action="${contextPath}/orders" method="POST"
    enctype="multipart/form-data" modelAttribute="order" class="order-form">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <div class="field">
      <label>Phone Number(*):</label>
      <form:input path="phoneNumber" placeholder="Your phone number" class="form-control"
        value="${currentUser != null ? currentUser.getProfile().getPhoneNumber() : null}" />
    </div>

    <div class="field">
      <label>Name(*):</label>
      <form:input path="name" placeholder="Your name" class="form-control"
        value="${currentUser != null ? currentUser.getName() : null}" />
    </div>

    <div class="field">
      <label>Email(*):</label>
      <form:input path="email" placeholder="Your email" class="form-control"
        value="${currentUser != null ? currentUser.getEmail() : null}" />
    </div>

    <div class="field">
      <label>Address:</label>
      <form:input path="address" placeholder="Your address" class="form-control"
        value="${currentUser != null ? currentUser.getProfile().getAddress() : null}" />
    </div>

    <div class="actions">
      <button type="button" class="btn btn-primary btn-order" data-toggle="modal"
        data-target="#preview-order">Save</button>
      <a href="${contextPath}/carts" class="btn btn-default btn-cancel-order">Cancel</a>
    </div>
    <div class="clearfix"></div>
  </form:form>

  <div id="preview-order" class="modal fade" role="dialog">
    <c:set var="carts" value="${carts}" scope="session" />
    <c:import url="/views/orders/pre_show.jsp" />
    <c:remove var="carts" scope="session" />
  </div>
</div>
