<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="suggest-header">
  <a href="${contextPath}/suggests/new" class="btn btn-primary btn-create-suggest">
    <i class="fa fa-plus"></i>
    Create suggest
  </a>
</div>

<div class="row">
  <h2 class="center">This is your suggests (${suggestsSize} suggests)</h2>
</div>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-body">
        <table class="table table-striped table-bordered table-hover suggests-table"
          id="dataTables-example" style="width: 100%">
          <thead class="">
            <tr>
              <th>#</th>
              <th data-orderable="false">Product avatar</th>
              <th>Product name</th>
              <th>Product information</th>
              <th>Product category</th>
              <th>Product price</th>
              <th>Created at</th>
              <th>Status</th>
              <th data-orderable="false">Action</th>
            </tr>
          </thead>

          <tbody>
            <c:forEach items="${suggests}" var="suggest" varStatus="loop">
              <tr class="${loop.index % 2 == 0 ? 'odd' : 'even'}">
                <td>${loop.index + 1}</td>
                <td>
                  <img src="${suggest.getAvatar()}" class="img-responsive suggest-avatar" />
                </td>
                <td>${suggest.getName()}</td>
                <td>${suggest.getInformation()}</td>
                <td>${suggest.getCategory()}</td>
                <td>
                  <fmt:setLocale value="en_US" />
                  <fmt:formatNumber value="${suggest.getPrice()}" type="currency" />
                </td>
                <td>${suggest.getCreatedAt()}</td>
                <td>
                  <strong class="suggest-status status-${orderProduct.getStatus()}">
                    ${suggest.getStatus()} </strong>
                </td>
                <td>
                  <c:set var="suggest" value="${suggest}" scope="session" />
                  <c:import url="/views/suggests/form.jsp" />
                  <c:remove var="suggest" scope="session" />
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
