<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
  <div class="col-sm-3"></div>
  <div class="col-sm-6">
    <div class="suggest-form">

      <h2>Edit suggest</h2>
      <c:if test="${message != null}">
        <div class="alert alert-warning">${message}</div>
      </c:if>

      <form:form id="update-suggest" action="${contextPath}/suggests/${suggest.getId()}" method="PATCH" 
        enctype="multipart/form-data" modelAttribute="suggest" class="form-suggest">
        <div class="field">
          <label>Avatar: </label>
          <form:input type="file" path="avatarFile" class="avatar" />
          <div class="suggest-avatar">
            <img src="${suggest.getAvatar()}" class="img-responsive suggest-avatar-panel" />
          </div>
          <form:errors path="avatar" style="color:red;"/>          
        </div>
        
        <div class="field">
          <label>Name: </label>
          <form:input path="name" placeholder="Name of product" class="form-control" />
        </div>
        
        <div class="field">    
          <label>Information: </label>
          <form:textarea path="information" placeholder="Information of product" class="form-control" />
        </div>
          
        <div class="field">
          <label>Category: </label>
          <form:input path="category" placeholder="Category of product" class="form-control" />
        </div>
        
        <div class="field">
          <label>Price($): </label>
          <form:input path="price" type="number" step="any" class="form-control" />
        </div>
        <input type="submit" class="btn btn-primary" value="Save" />
      </form:form>
    </div>
  </div>
  <div class="col-sm-3"></div>
</div>
