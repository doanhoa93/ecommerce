<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/views/pages/hot_trend_product.jsp" />

<section>
  <div class="container">
    <div class="row page-div">
      <div class="col-sm-3">
        <%@include file="menu_category.jsp"%>
      </div>

      <div class="col-sm-9 padding-right">
        <c:import url="/views/pages/recently_product.jsp" />
        <c:import url="/views/pages/hot_trend_category.jsp" />
        <c:import url="/views/pages/recommend_product.jsp" />
      </div>

      <c:if test="${currentUser != null}">
        <div class="col-lg-4 chat-div">
          <c:import url="/views/chats/index.jsp" />
          <c:import url="/views/chats/chat_title.jsp" />
        </div>
      </c:if>
    </div>
  </div>
</section>
