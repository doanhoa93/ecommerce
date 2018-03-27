<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="new-order">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">New order</h4>
      </div>

      <div class="modal-body">
        <form:form id="new-order" action="${contextPath}/orders" method="POST"
          enctype="multipart/form-data" modelAttribute="orderInfo" class="order-form">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <div class="field">
            <label>Phone Number(*):</label>
            <form:input path="phoneNumber" placeholder="Your phone number" class="form-control" />
          </div>

          <div class="field">
            <label>Name(*):</label>
            <form:input path="name" placeholder="Your name" class="form-control" />
          </div>

          <div class="field">
            <label>Email(*):</label>
            <form:input path="email" placeholder="Your email" class="form-control" />
          </div>

          <div class="field">
            <label>Address:</label>
            <form:input path="address" placeholder="Your address" class="form-control" />
          </div>

          <div class="actions">
            <input type="submit" class="btn btn-primary btn-order" value="Save" />
            <button class="btn btn-default btn-cancel-order" data-dismiss="modal">Cancel</button>
          </div>
          <div class="clearfix"></div>
        </form:form>
      </div>
    </div>
  </div>
</div>
