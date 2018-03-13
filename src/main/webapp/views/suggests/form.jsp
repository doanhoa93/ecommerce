<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:if test="${suggest.getStatus() == 'WAITING'}">
  <a href="${contextPath}/suggests/${suggest.getId()}/edit" class="btn btn-primary btn-suggest">
    Edit suggest
  </a>
  
  <form:form action="${contextPath}/suggests/${suggest.getId()}" method="DELETE" 
    data-confirm="Delete this suggest, are you sure?">
    <input type="submit" class="btn btn-danger btn-suggest" value="Delete" />
  </form:form>
</c:if>
