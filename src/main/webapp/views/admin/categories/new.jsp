<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="admin-new-category">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">
          &times;
        </button>
        <h4 class="modal-title">New category</h4>
      </div>
      <div class="modal-body">
        <form:form id="create-category" action="${contextPath}/admin/categories" method="POST" 
          modelAttribute="category" enctype="multipart/form-data">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <div class="field">
            <label>Name of new category</label>
            <form:input path="name" placeholder="Name of category" class="form-control" />
          </div>
          
          <div class="field">
            <label>Parent of new category</label>
            <form:input path="parentId" type="hidden" id="parent-input-hidden" />
            <form:input path="parentName" type="hidden" id="parent-input-name-hidden" />          
            <input list="parentCategories" placeholder="Name of Parent" 
              id="parent-input" class="form-control" />
            <datalist id="parentCategories">
              <c:forEach var="category" items="${categories}">
                <option value="${category.getName()}" data-value="${category.getId()}" />
              </c:forEach>
            </datalist>
          </div>          
          
          <div class="actions">
            <input type="submit" class="btn btn-primary btn-create-category" value="Create" />
            <button class="btn btn-default btn-create-category" data-dismiss="modal">Cancel</button>
            <div class="clearfix"></div>
          </div>
        </form:form>        
      </div>
    </div>
  </div>
</div>
