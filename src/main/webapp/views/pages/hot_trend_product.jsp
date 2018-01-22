<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="slider">
  <!--slider-->
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <div id="slider-carousel" class="carousel slide"
          data-ride="carousel">
          <ol class="carousel-indicators">
            <c:forEach var="i" begin="0"
              end="${params.hotProducts.size() - 1}">
              <li data-target="#slider-carousel" data-slide-to="${i}"
                class="${i == 0 ? 'active' : '' }"></li>
            </c:forEach>
          </ol>

          <div class="carousel-inner">
            <c:forEach var="product" items="${params.hotProducts}"
              varStatus="loop">
              <c:import url="/views/products/medium_product.jsp" />
              <c:set var="index" value="${loop.index}" scope="session" />
              <c:set var="product" value="${product}" scope="session" />
            </c:forEach>
          </div>

          <a href="#slider-carousel"
            class="left control-carousel hidden-xs" data-slide="prev">
            <i class="fa fa-angle-left"></i>
          </a> <a href="#slider-carousel"
            class="right control-carousel hidden-xs" data-slide="next">
            <i class="fa fa-angle-right"></i>
          </a>
        </div>
      </div>
    </div>
  </div>
</section>
<!--/slider-->