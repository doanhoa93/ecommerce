<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
  <div class="col-lg-12 page-header">
    <h1>Promotions</h1>
    <a data-href="${contextPath}/admin/promotions/new" class="btn btn-primary new-promotion">New
      promotions</a>
  </div>
</div>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-body">
        <table class="table table-striped table-bordered table-hover admin-promotion-table"
          id="dataTables-example" style="width: 100%">
          <thead>
            <tr>
              <th>#</th>
              <th>Start date</th>
              <th>End date</th>
              <th>SaleOf (%)</th>
              <th>Control</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${promotions}" var="promotion" varStatus="loop">
              <tr class="${loop.index % 2 == 0 ? 'odd' : 'even'} promotion-${promotion.getId()}">
                <td>${loop.index + 1}</td>
                <td>${promotion.getStartDate()}</td>
                <td>${promotion.getEndDate()}</td>
                <td>${promotion.getSaleOf()}</td>
                <td>
                  <a data-href="${contextPath}/admin/promotions/${promotion.getId()}/edit"
                    class="btn btn-primary btn-promotion-control edit-promotion">Edit this
                    promotion</a>

                  <form:form action="${contextPath}/admin/promotions/${promotion.getId()}"
                    method="DELETE">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="submit" class="btn btn-danger"
                      data-confirm="Delete this promotion, are you sure?"
                      value="Delete this promotion">
                  </form:form>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <div id="form-promotion" class="modal fade" role="dialog"></div>
      </div>
    </div>
  </div>
</div>
