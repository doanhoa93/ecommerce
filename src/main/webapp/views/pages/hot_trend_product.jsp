<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="hotProducts" value="${params.hotProducts}" scope="page" />
<section id="slider">
  <!--slider-->
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <div id="slider-carousel" class="carousel slide"
          data-ride="carousel">
          <ol class="carousel-indicators">
            <c:forEach items="${hotProducts}" varStatus="loop">
              <li data-target="#slider-carousel"
                data-slide-to="${loop.index - 1}"
                class="${(loop.index - 1) == 0 ? 'active' : '' }"></li>
            </c:forEach>
          </ol>

          <div class="carousel-inner">
            <c:forEach var="product" items="${hotProducts}"
              varStatus="loop">
              <div class="item ${loop.index == 1 ? 'active' : ''}">
                <div class="col-sm-6">
                  <h1>
                    <span>ECOMMERCE</span>
                  </h1>
                  <h2>${product.getName()}</h2>
                  <p>${product.getInformation()}</p>
                  <button type="button" class="btn btn-default get">Get
                    it now</button>
                </div>
                <div class="col-sm-6">
                  <img src="${contextPath}/${product.getAvatar()}"
                    class="girl img-responsive" alt="" />
                </div>
              </div>
            </c:forEach>
          </div>

          <a href="#slider-carousel"
            class="left control-carousel hidden-xs" data-slide="prev">
            <i class="fa fa-angle-left"></i>
          </a> 
          <a href="#slider-carousel"
            class="right control-carousel hidden-xs" data-slide="next">
            <i class="fa fa-angle-right"></i>
          </a>
        </div>
      </div>
    </div>
  </div>
</section>
<!--/slider-->
