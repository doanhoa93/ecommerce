<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
  <div class="col-lg-12 page-header">
    <h1>Categories</h1>
    <a data-href="${contextPath}/admin/categories/new" class="btn btn-primary new-category">New
      category</a>
  </div>
</div>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-body">
        <table class="table table-striped table-bordered table-hover admin-category-table"
          id="dataTables-example" style="width: 100%">
          <thead>
            <tr>
              <th>#</th>
              <th>Name</th>
              <th>Products</th>
              <th>Created at</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${categories}" var="category" varStatus="loop">
              <tr class="${loop.index % 2 == 0 ? 'odd' : 'even'} tr-href"
                data-href="${contextPath}/admin/categories/${category.getId()}">
                <td>${loop.index + 1}</td>
                <td>${category.getName()}</td>
                <td>${category.getProducts().size()}</td>
                <td>${category.getCreatedAt()}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
