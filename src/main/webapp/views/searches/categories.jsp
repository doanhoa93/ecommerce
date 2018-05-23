<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
  <c:when test="${categories.size() > 0}">
    <c:forEach var="category" items="${categories}">
      <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6">
        <c:set var="category" value="${category}" scope="session" />
        <c:import url="/views/categories/category.jsp" />
        <c:remove var="category" scope="session" />
      </div>
    </c:forEach>
  </c:when>

  <c:otherwise>
    <h2>No categories</h2>
  </c:otherwise>
</c:choose>
