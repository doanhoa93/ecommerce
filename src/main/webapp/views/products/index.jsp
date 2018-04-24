<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="products">
  <div class="products-left col-md-3 col-lg-3 col-sm-12 col-xs-12">
    <div class="brands_products">
      <h2>Categories</h2>
      <input type="text" name="name" placeholder="Name of categories" class="name-category">
      <div class="brands-name categories">
        <ul class="nav nav-pills nav-stacked">
          <c:forEach var="category" items="${categories}">
            <li class="category" data-id="${category.getId()}">
              <a href="${contextPath}/categories/${category.getId()}"
                class="${categoryId == category.getId() ? 'active' : ''}">
                <span class="category-name">${category.getName()}</span>
                <span class="pull-right category-size">(${category.getProducts().size()})</span>
              </a>
            </li>
          </c:forEach>
        </ul>
      </div>
    </div>

    <div class="name-range">
      <h3>Name range</h3>
      <div class="ui-widget">
        <input type="text" name="name" placeholder="Name of product" class="name-range-input">
      </div>
    </div>

    <div class="price-range">
      <h3>Price Range</h3>
      <div class="price text-center">
        <input type="range" class="price-range-input" value="${maxPrice}" min="${minPrice}"
          max="${maxPrice}" step="5" id="sl2">
        <br />
        <b class="pull-left">
          <fmt:setLocale value="en_US" />
          <fmt:formatNumber value="${minPrice}" type="currency" />
        </b>
        <b class="pull-right">
          <fmt:setLocale value="en_US" />
          <fmt:formatNumber value="${maxPrice}" type="currency" />
        </b>
      </div>
    </div>

    <button class="btn btn-primary btn-filter">
      <i class="fa fa-filter" aria-hidden="true"></i>
      Filter
    </button>
  </div>

  <div class="col-md-9 col-lg-9 col-sm-12 col-xs-12">
    <div class="product-orders">
      <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <span>
          <b>Price:</b>
        </span>
        <span>
          <select name="price"
            class="form-control product-order-field order-price product-order-active">
            <option value="asc">From Low to High</option>
            <option value="desc">From High to Low</option>
          </select>
        </span>
      </div>

      <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <span>
          <b>Name:</b>
        </span>
        <span>
          <select name="name" class="form-control product-order-field order-name">
            <option class="empty" selected></option>
            <option value="asc">A-Z</option>
            <option value="desc">Z-A</option>
          </select>
        </span>
      </div>
      <div class="clearfix"></div>
    </div>
    <hr>

    <div class="products-right">
      <c:set var="products" value="${products}" scope="session" />
      <c:import url="/views/products/products.jsp" />
      <c:remove var="products" scope="session" />
    </div>
  </div>
</div>
