<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="recommended_items">
  <h2 class="title text-center">Recommended Products</h2>
  <div id="recommended-item-carousel" class="carousel slide"
    data-ride="carousel">
    <div class="carousel-inner">
      <div class="item active">
        <c:forEach var="product" items="${recommendProducts}" begin="0" end="2">
          <div class="col-sm-4">
            <c:set var="product" value="${product}" scope="session" />
            <c:import url="/views/products/mini_product.jsp" />
            <c:remove var="product" scope="session" />
          </div>
        </c:forEach>
      </div>

      <div class="item">
        <c:forEach var="product" items="${recommendProducts}" begin="3" end="5">
          <div class="col-sm-4">
            <c:set var="product" value="${product}" scope="session" />
            <c:import url="/views/products/mini_product.jsp" />
            <c:remove var="product" scope="session" />
          </div>
        </c:forEach>
      </div>
    </div>

    <a class="left recommended-item-control"
      href="#recommended-item-carousel" data-slide="prev"> 
      <i class="fa fa-angle-left"></i>
    </a> 
    <a class="right recommended-item-control"
      href="#recommended-item-carousel" data-slide="next"> 
      <i class="fa fa-angle-right"></i>
    </a>
  </div>
</div>
