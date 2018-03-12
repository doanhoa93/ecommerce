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
       <input type="text" name="name" placeholder="Name of product" class="name-range-input">
    </div>
  
    <div class="price-range">
      <h3>Price Range</h3>
      <div class="price text-center">
        <input type="range" class="price-range-input" value="${maxPrice / 2}" min="${minPrice}" 
          max="${maxPrice}" step="5" id="sl2"><br />
          <b class="pull-left">
            <fmt:setLocale value="en_US" />
            <fmt:formatNumber value="${minPrice}" type="currency" />        
          </b>
          <b class="price-value">
            Value:
            <fmt:setLocale value="en_US" />
            <fmt:formatNumber value="${maxPrice / 2}" type="currency" minFractionDigits="0"/>        
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
  
  <div class="products-right col-md-9 col-lg-9 col-sm-12 col-xs-12">
    <tiles:insertAttribute name="products" ignore="true"  />
  </div>
</div>
