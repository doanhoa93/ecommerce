<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="features_items">
  <!--features_items-->
  <h2 class="title text-center">Recently Products</h2>
  <c:forEach var="i" begin="1" end="6">
    <div class="col-sm-4">
      <jsp:include page="/views/products/mini_product.jsp">
        <jsp:param name="imageSource"
          value="${pageContext.request.contextPath}/assets/images/home/product${i}.jpg" />
      </jsp:include>
    </div>
  </c:forEach>
</div>
<!--features_items-->