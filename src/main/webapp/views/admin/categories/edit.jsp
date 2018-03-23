<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="admin-edit-category">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">
          &times;
        </button>
        <h4 class="modal-title">Edit category</h4>
      </div>
      <div class="modal-body">
        <form:form id="update-category" action="${contextPath}/admin/categories/${category.getId()}" method="PATCH"
          enctype="multipart/form-data" modelAttribute="category" class="category-form">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <div class="field">
            <label>Name of category</label>
            <form:input path="name" placeholder="Name of category" class="form-control" />
          </div>
          
          <div class="field">
            <label>Parent of category</label>
            <form:input path="parentId" type="hidden" id="parent-input-hidden" value="${category.getParentId()}" />
            <form:input path="parentName" type="hidden" id="parent-input-name-hidden" />     
            <input list="parentCategories" value="${category.getParentName()}" placeholder="Name of Parent" 
              id="parent-input" class="form-control" />
            <datalist id="parentCategories">
              <c:forEach var="category" items="${categories}">
                <option value="${category.getName()}" data-value="${category.getId()}" />
              </c:forEach>
            </datalist>
          </div>          
          
          <div class="actions">
            <input type="submit" class="btn btn-primary btn-update-category" value="Save" />
            <button class="btn btn-default btn-update-category" data-dismiss="modal">Cancel</button>
            <div class="clearfix"></div>
          </div>
        </form:form>        
      </div>
    </div>
  </div>
</div>
