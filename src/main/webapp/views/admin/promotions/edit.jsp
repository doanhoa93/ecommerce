<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="admin-new-promotion">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Edit promotion</h4>
      </div>
      <div class="modal-body">
        <form:form id="update-promotion"
          action="${contextPath}/admin/promotions/${promotion.getId()}" method="PATCH"
          modelAttribute="promotion" enctype="multipart/form-data">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <div class="field">
            <label>Start date</label>
            <fmt:formatDate value="${promotion.getStartDate()}" pattern="MM/dd/yyyy" var="startDate" />
            <form:input path="startDate" placeholder="Start date of promotion"
              class="form-control datepicker" data-provide="datepicker" value="${startDate}" />
          </div>

          <div class="field">
            <label>End date</label>
            <fmt:formatDate value="${promotion.getEndDate()}" pattern="MM/dd/yyyy" var="endDate" />
            <form:input path="endDate" placeholder="End date of promotion"
              class="form-control datepicker" data-provide="datepicker" value="${endDate}" />
          </div>

          <div class="field">
            <label>Sale of (%)</label>
            <form:input path="saleOf" type="number" step="any" min="${saleOf['min']}"
              max="${saleOf['max']}" class="form-control" />
          </div>

          <div class="actions">
            <input type="submit" class="btn btn-primary btn-create-promotion" value="Save" />
            <button class="btn btn-default btn-create-promotion" data-dismiss="modal">Cancel</button>
            <div class="clearfix"></div>
          </div>
        </form:form>
      </div>
    </div>
  </div>
</div>
