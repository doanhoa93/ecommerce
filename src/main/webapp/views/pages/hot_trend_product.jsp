<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="slider">
  <!--slider-->
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <div id="slider-carousel" class="carousel slide"
          data-ride="carousel">
          <ol class="carousel-indicators">
            <c:forEach var="i" begin="0" end="2">
              <li data-target="#slider-carousel" data-slide-to="${i}"
                class="${i == 0 ? 'active' : '' }"></li>
            </c:forEach>
          </ol>

          <div class="carousel-inner">
            <c:forEach var="i" begin="1" end="3">
              <jsp:include page="/views/products/medium_product.jsp">
                <jsp:param name="index" value="${i}" />
                <jsp:param name="imageSource"
                  value="${pageContext.request.contextPath}/assets/images/home/girl${i}.jpg" />
              </jsp:include>
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