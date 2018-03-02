<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
  <div class="col-lg-12 page-header">
    <h1>Products</h1>
    <a href="${contextPath}/admin/products/new" class="btn btn-primary new-product">New product</a>
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
              <th>Category</th>
              <th>Price</th>
              <th>Number</th>
              <th>Orders</th>
              <th>Created at</th>              
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${products}" var="product" varStatus="loop">
              <tr class="${loop.index % 2 == 0 ? 'odd' : 'even'} tr-href product-${product.getId()}"
                data-href="${contextPath}/admin/products/${product.getId()}">
                <td>${loop.index + 1}</td>
                <td><img src="${product.getAvatar()}" class="img-responsive admin-product-avatar"></td>
                <td>${product.getName()}</td>
                <td>${product.getCategory().getName()}</td>
                <td>
                  <fmt:setLocale value="en_US" />
                  <fmt:formatNumber value="${product.getPrice()}" type="currency" />                
                </td>
                <td>${product.getNumber()}</td>
                <td>${product.getOrderProducts().size()}</td>
                <td>${product.getCreatedAt()}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <div id="form-product" class="modal fade" role="dialog"></div>        
      </div>
    </div>
  </div>
</div>
