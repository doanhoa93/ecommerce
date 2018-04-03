<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="error" items="${errors}">
  <div class="error ${error.key}" data-name="${error.key}">${error.value}</div>
</c:forEach>
