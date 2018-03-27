<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="uri" value="${request.getRequestURI()}?page=" />
<c:set var="page" value="${param.page}" />

<c:choose>
  <c:when test="${paginateJS}">
    <ul class="pagination pagination-sm">
      <c:if test="${paginate.start > 1}">
        <li>
          <a class="paginate-a" data-href="${uri}${paginate.start == 1 ? 1 : paginate.start - 1}">
            Previous </a>
        </li>
      </c:if>

      <c:forEach begin="${paginate.start}" end="${paginate.end}" var="p">
        <li class="${(page == p || (page == null && paginate.start == p)) ? 'active' : ''}">
          <a class="paginate-a" data-href="${uri}${p}">${p}</a>
        </li>
      </c:forEach>

      <c:if test="${paginate.more}">
        <li>
          <a class="paginate-a" data-href="${uri}${paginate.end + 1}">Next</a>
        </li>
      </c:if>
    </ul>
  </c:when>

  <c:otherwise>
    <ul class="pagination pagination-sm">
      <c:if test="${paginate.start > 1}">
        <li>
          <a href="${uri}${paginate.start == 1 ? 1 : paginate.start - 1}"> Previous </a>
        </li>
      </c:if>

      <c:forEach begin="${paginate.start}" end="${paginate.end}" var="p">
        <li class="${(page == p || (page == null && paginate.start == p)) ? 'active' : ''}">
          <a href="${uri}${p}">${p}</a>
        </li>
      </c:forEach>

      <c:if test="${paginate.more}">
        <li>
          <a href="${uri}${paginate.end + 1}">Next</a>
        </li>
      </c:if>
    </ul>
  </c:otherwise>
</c:choose>
