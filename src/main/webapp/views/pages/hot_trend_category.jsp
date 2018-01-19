<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="category-tab">
  <!--category-tab-->
  <div class="col-sm-12">
    <ul class="nav nav-tabs">
      <li class="active"><a href="#tshirt" data-toggle="tab">T-Shirt</a></li>
      <li><a href="#blazers" data-toggle="tab">Blazers</a></li>
      <li><a href="#sunglass" data-toggle="tab">Sunglass</a></li>
      <li><a href="#kids" data-toggle="tab">Kids</a></li>
      <li><a href="#poloshirt" data-toggle="tab">Polo shirt</a></li>
    </ul>
  </div>
  
  <div class="tab-content">
    <div class="tab-pane fade active in" id="tshirt">
      <c:forEach var="i" begin="1" end="4">
        <div class="col-sm-3">
          <jsp:include page="/views/products/mini_product.jsp">
            <jsp:param name="imageSource"
              value="${pageContext.request.contextPath}/assets/images/home/gallery${i}.jpg" />
          </jsp:include>
        </div>
      </c:forEach>
    </div>

    <div class="tab-pane fade" id="blazers">
      <c:forEach var="i" begin="1" end="5">
        <div class="col-sm-3">
          <jsp:include page="/views/products/mini_product.jsp">
            <jsp:param name="imageSource"
              value="${pageContext.request.contextPath}/assets/images/home/gallery${5 - i}.jpg" />
          </jsp:include>
        </div>
      </c:forEach>
    </div>

    <div class="tab-pane fade" id="sunglass">
      <c:forEach var="i" begin="1" end="4">
        <div class="col-sm-3">
          <jsp:include page="/views/products/mini_product.jsp">
            <jsp:param name="imageSource"
              value="${pageContext.request.contextPath}/assets/images/home/gallery${i}.jpg" />
          </jsp:include>
        </div>
      </c:forEach>
    </div>

    <div class="tab-pane fade" id="kids">
      <c:forEach var="i" begin="1" end="4">
        <div class="col-sm-3">
          <jsp:include page="/views/products/mini_product.jsp">
            <jsp:param name="imageSource"
              value="${pageContext.request.contextPath}/assets/images/home/gallery${5 - i}.jpg" />
          </jsp:include>
        </div>
      </c:forEach>
    </div>

    <div class="tab-pane fade" id="poloshirt">
      <c:forEach var="i" begin="1" end="4">
        <div class="col-sm-3">
          <jsp:include page="/views/products/mini_product.jsp">
            <jsp:param name="imageSource"
              value="${pageContext.request.contextPath}/assets/images/home/gallery${i}.jpg" />
          </jsp:include>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
<!--/category-tab-->