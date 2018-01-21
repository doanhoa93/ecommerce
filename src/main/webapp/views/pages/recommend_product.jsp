<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="recommended_items">
  <!--recommended_items-->
  <h2 class="title text-center">Recommended Products</h2>
  <div id="recommended-item-carousel" class="carousel slide"
    data-ride="carousel">
    <div class="carousel-inner">
      <div class="item active">
        <c:forEach var="i" begin="1" end="3">
          <div class="col-sm-4">
            <jsp:include page="/views/products/mini_product.jsp">
              <jsp:param name="imageSource"
                value="${pageContext.request.contextPath}/assets/images/home/recommend${i}.jpg" />
            </jsp:include>
          </div>
        </c:forEach>
      </div>

      <div class="item">
        <c:forEach var="i" begin="1" end="3">
          <div class="col-sm-4">
            <jsp:include page="/views/products/mini_product.jsp">
              <jsp:param name="imageSource"
                value="${pageContext.request.contextPath}/assets/images/home/recommend${4 - i}.jpg" />
            </jsp:include>
          </div>
        </c:forEach>
      </div>
    </div>

    <a class="left recommended-item-control"
      href="#recommended-item-carousel" data-slide="prev"> <i
      class="fa fa-angle-left"></i>
    </a> <a class="right recommended-item-control"
      href="#recommended-item-carousel" data-slide="next"> <i
      class="fa fa-angle-right"></i>
    </a>
  </div>
</div>
