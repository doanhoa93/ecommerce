<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
  <div class="col-lg-12 page-header">
    <h1>${category.getName()}</h1>
    <div class="category-parent">
      Parent:
      <a href="${contextPath}/admin/categories/${category.getParentId()}">${category.getParentName()}</a>
    </div>

    <a data-href="${contextPath}/admin/categories/${category.getId()}/edit"
      class="btn btn-primary edit-category"> Edit this category </a>

    <form:form action="${contextPath}/admin/categories/${category.getId()}" method="DELETE"
      class="delete-category">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      <input type="submit" class="btn btn-danger" value="Delete this category">
    </form:form>
  </div>
</div>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-body">
        <table class="table table-striped table-bordered table-hover admin-product-table"
          id="dataTables-example" style="width: 100%">
          <thead>
            <tr>
              <th>#</th>
              <th>Avatar</th>
              <th>Name</th>
              <th>Price</th>
              <th>Number</th>
              <th>Orders</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${products}" var="product" varStatus="loop">
              <tr class="${loop.index % 2 == 0 ? 'odd' : 'even'} tr-href product-${product.getId()}"
                data-href="${contextPath}/admin/products/${product.getId()}">
                <td>${loop.index + 1}</td>
                <td>
                  <img src="${product.getAvatar()}" class="img-responsive admin-product-avatar">
                </td>
                <td>${product.getName()}</td>
                <td>
                  <fmt:setLocale value="en_US" />
                  <fmt:formatNumber value="${product.getPrice()}" type="currency" />
                </td>
                <td>${product.getNumber()}</td>
                <td>${product.getOrderProducts().size()}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <div id="form-product" class="modal fade" role="dialog"></div>
      </div>
    </div>
  </div>
</div>
