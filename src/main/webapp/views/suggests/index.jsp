<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
  <c:when test="${suggests.size() > 0}">
    <div class="suggest-header">
      <a href="${contextPath}/suggests/new" class="btn btn-primary btn-create-suggest">
        <i class="fa fa-plus"></i>Create suggest
      </a>
      <h2 class="center">This is your suggests (${suggestsSize} suggests)</h2>
    </div>
    <table class="table table-bordered suggests-table">
      <thead class="">
        <tr>
          <th>#</th>
          <th>Product avatar</th>
          <th>Product name</th>
          <th>Product information</th>
          <th>Product category</th>
          <th>Product price</th>
          <th>Created at</th>
          <th>Status</th>
        </tr>
      </thead>
    
      <tbody>
        <c:forEach items="${suggests}" var="suggest" varStatus="loop">
          <tr>
            <td>${loop.index + 1}</td>
            <td><img src="${suggest.getAvatar()}" class="img-responsive suggest-avatar" /></td>
            <td>${suggest.getName()}</td>
            <td>${suggest.getInformation()}</td>
            <td>${suggest.getCategory()}</td>
            <td>
              <fmt:setLocale value="en_US" />
              <fmt:formatNumber value="${suggest.getPrice()}" type="currency" />        
            </td>
            <td>${suggest.getCreatedAt()}</td>
            <td>${suggest.getStatus()}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    
    <div class="suggest-paginate">
      <c:set var="paginate" value="${paginate}" scope="session" />
      <c:import url="/views/shared/paginate.jsp" />
      <c:remove var="paginate" scope="session" />
    </div>
  </c:when>
  
  <c:otherwise>
    <h3>No suggests</h3>
  </c:otherwise>
</c:choose>
