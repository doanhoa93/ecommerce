<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="uri" value="${request.getRequestURI()}${param.size() == 0 ? '' : '?'}" />
<c:forEach var="pageParameter" items="${param}">
  <c:if test="${pageParameter.key != 'page'}">
    <c:set var="uri" value="${uri}&${pageParameter.key}=${pageParameter.value}" />
  </c:if>
</c:forEach>
<c:set var="uri" value="${uri}&page=" />
<c:set var="page" value="${param.page}" />

<c:if test="${paginate.start != paginate.end || (paginate.next == true || paginate.prev == true)}">
  <c:choose>
    <c:when test="${paginateJS}">
      <ul class="pagination pagination-sm">
        <c:if test="${paginate.prev}">
          <li>
            <a class="paginate-a" data-href="${uri}${paginate.start - 1}">Previous</a>
          </li>
        </c:if>

        <c:forEach begin="${paginate.start}" end="${paginate.end}" var="p">
          <li class="${(page == p || (page == null && paginate.start == p)) ? 'active' : ''}">
            <a class="paginate-a" data-href="${uri}${p}">${p}</a>
          </li>
        </c:forEach>

        <c:if test="${paginate.next}">
          <li>
            <a class="paginate-a" data-href="${uri}${paginate.end + 1}">Next</a>
          </li>
        </c:if>
      </ul>
    </c:when>

    <c:otherwise>
      <ul class="pagination pagination-sm">
        <c:if test="${paginate.prev}">
          <li>
            <a class="paginate-a" href="${uri}${paginate.start - 1}">Previous</a>
          </li>
        </c:if>

        <c:forEach begin="${paginate.start}" end="${paginate.end}" var="p">
          <li class="${(page == p || (page == null && paginate.start == p)) ? 'active' : ''}">
            <a class="paginate-a" href="${uri}${p}">${p}</a>
          </li>
        </c:forEach>

        <c:if test="${paginate.next}">
          <li>
            <a class="paginate-a" href="${uri}${paginate.end + 1}">Next</a>
          </li>
        </c:if>
      </ul>
    </c:otherwise>
  </c:choose>
</c:if>
