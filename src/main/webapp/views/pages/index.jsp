<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/views/pages/hot_trend_product.jsp" />

<section>
  <div class="container">
    <div class="row">
      <div class="col-sm-3">
        <%@include file="menu_category.jsp"%>
      </div>

      <div class="col-sm-9 padding-right">
        <c:import url="/views/pages/recently_product.jsp" />
        <c:import url="/views/pages/hot_trend_category.jsp" />
        <c:import url="/views/pages/recommend_product.jsp" />
      </div>
    </div>
  </div>
</section>
