<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Suggests</h1>
  </div>
</div>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-body">
        <table class="table table-striped table-bordered table-hover admin-suggest-table"
          id="dataTables-example" style="width: 100%">
          <thead>
            <tr>
              <th>#</th>
              <th>Customer</th>
              <th>Product name</th>
              <th>Product category</th>
              <th>Product price</th>
              <th>Created at</th>
              <th>Status</th>       
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${suggests}" var="suggest" varStatus="loop">
              <tr class="${loop.index % 2 == 0 ? 'odd' : 'even'} tr-href-js suggest-${suggest.getId()}"
                data-href="${contextPath}/admin/suggests/${suggest.getId()}">
                <td>${loop.index + 1}</td>
                <td>${suggest.getUser().getName()}</td>
                <td>${suggest.getName()}</td>
                <td>${suggest.getCategory()}</td>
                <td>
                  <fmt:setLocale value="en_US" />
                  <fmt:formatNumber value="${suggest.getPrice()}" type="currency" />
                </td>
                <td>${suggest.getCreatedAt()}</td>
                <td class="center suggest-status status-${suggest.getStatus()}">
                  ${suggest.getStatus()}
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <div id="form-suggest" class="modal fade" role="dialog"></div>
      </div>
    </div>
  </div>
</div>
