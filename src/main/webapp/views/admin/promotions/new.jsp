<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="admin-new-promotion">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">New promotion</h4>
      </div>
      <div class="modal-body">
        <form:form id="create-promotion" action="${contextPath}/admin/promotions" method="POST"
          modelAttribute="promotion" enctype="multipart/form-data">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <div class="field">
            <label>Start date</label>
            <form:input path="startDate" placeholder="Start date of promotion"
              class="form-control datepicker" data-provide="datepicker" />
          </div>

          <div class="field">
            <label>End date</label>
            <form:input path="endDate" placeholder="End date of promotion"
              class="form-control datepicker" data-provide="datepicker" />
          </div>

          <div class="field">
            <label>Sale of (%)</label>
            <form:input path="saleOf" type="number" step="any" min="${saleOf['min']}"
              max="${saleOf['max']}" class="form-control" />
          </div>

          <div class="actions">
            <input type="submit" class="btn btn-primary btn-create-promotion" value="Create" />
            <button class="btn btn-default btn-create-promotion" data-dismiss="modal">Cancel</button>
            <div class="clearfix"></div>
          </div>
        </form:form>
      </div>
    </div>
  </div>
</div>
