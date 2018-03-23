<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
  <div class="col-sm-3"></div>
  <div class="col-sm-6">
    <div class="suggest-form">

      <h2>Create new suggest</h2>
      <c:if test="${message != null}">
        <div class="alert alert-warning">${message}</div>
      </c:if>

      <form:form id="create-suggest" action="${contextPath}/suggests" method="POST" 
        enctype="multipart/form-data" modelAttribute="suggest" class="form-suggest">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="field">
          <label>Avatar</label>
          <form:input type="file" path="avatarFile" class="avatar" />
          <div class="suggest-avatar">
            <img src="" class="img-responsive suggest-avatar-panel" />
          </div>
        </div>
        
        <div class="field">
          <label>Name (*)</label>
          <form:input path="name" placeholder="Name of product" class="form-control" />
        </div>
        
        <div class="field">    
          <label>Information (*)</label>
          <form:textarea path="information" placeholder="Information of product" class="form-control" />
        </div>
          
        <div class="field">
          <label>Category </label>
          <form:input path="category" placeholder="Category of product" class="form-control" />
        </div>
        
        <div class="field">
          <label>Price($) (*)</label>
          <form:input path="price" type="number" step="any" min="0" class="form-control" />
        </div>
        
        <div class="actions">
          <a href="${contextPath}/suggests" class="btn btn-default btn-suggest">Cancel</a>
          <input type="submit" class="btn btn-primary btn-suggest" value="Create" />
        </div>
      </form:form>
    </div>
  </div>
  <div class="col-sm-3"></div>
</div>
