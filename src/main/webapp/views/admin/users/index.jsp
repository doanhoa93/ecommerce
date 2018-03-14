<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Users</h1>
  </div>
</div>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-body">
        <table class="table table-striped table-bordered table-hover admin-user-table"
          id="dataTables-example" style="width: 100%">
          <thead>
            <tr>
              <th>#</th>
              <th>Email</th>
              <th>Name</th>
              <th>Gender</th>
              <th>Carts</th>
              <th>Orders</th>
              <th>Created at</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${users}" var="user" varStatus="loop">
              <tr class="${loop.index % 2 == 0 ? 'odd' : 'even'}">
                <td>${loop.index + 1}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getName()}</td>
                <td>${user.getProfile().getGender()}</td>
                <td>${user.getCarts().size()}</td>
                <td>${user.getOrders().size()}</td>
                <td>${user.getCreatedAt()}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
