<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="left-sidebar">
  <div class="brands_products">
    <h2>Categories</h2>
    <div class="brands-name">
      <ul class="nav nav-pills nav-stacked">
        <c:forEach var="category" items="${categories}">
          <li>
            <a href="#"> 
              <span class="pull-right">(${category.getProducts().size()})</span> 
              ${category.getName()}
            </a>
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>

  <div class="shipping text-center">
    <img src="${contextPath}/assets/images/home/shipping.jpg" alt="" />
  </div>
</div>
