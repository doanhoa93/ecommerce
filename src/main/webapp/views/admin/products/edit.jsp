<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="admin-edit-product">
  <h2 class="header">Edit product: ${product.getName()}</h2>
  <div class="admin-edit-product-body">
    <form:form id="update-product" action="${contextPath}/admin/products/${product.getId()}" 
      method="POST"  modelAttribute="product" enctype="multipart/form-data" class="edit-form product-form">
      <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <div class="field">
          <label>Avatar of product</label>
          <form:input type="file" path="avatarFile" class="avatar" />
          <div class="product-avatar">
            <img class="img-responsive product-avatar-panel" src="${product.getAvatar()}" />
          </div>
        </div>      
        
        <div class="field">   
          <label class="product-image-label">Images of product</label>
          <div class="product-image-forms">
            <c:forEach var="image" items="${product.getImages()}">
              <i class="fa fa-minus remove-image" aria-hidden="true"> Remove image</i>
              <div class="product-image-form">
                <form:input type="hidden" path="imagesStatus" value="1" />
                <form:input type="hidden" path="imageIds" value="${image.getId()}" />
                <form:input type="file" path="imageFiles" class="image" />
                <div class="product-image">
                  <img class="img-responsive" src="${image.getImage()}" />
                </div>
                <hr>                              
              </div>
            </c:forEach>            
          </div>     
          <i class="fa fa-plus add-image" aria-hidden="true"> Add image</i>                      
        </div>                       
      </div>  
            
      <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <div class="field">
          <label>Name of Product (*)</label>
          <form:input path="name" placeholder="Name of product" class="form-control" />
        </div>
        
        <div class="field">
          <label>Price of Product ($)</label>
          <form:input path="price" type="number" step="any" min="0" placeholder="Price of product" class="form-control" />
        </div>
        
        <div class="field">
          <label>Category of Product (*)</label>
          <form:select path="categoryId" class="form-control">
            <c:forEach var="category" items="${categories}">
              <form:option value="${category.getId()}" 
                selected="${category.getId() == product.getCategory().getId() ? 'selected' : ''}">
                ${category.getName()}
              </form:option>              
            </c:forEach>
          </form:select>
        </div>    
        
        <div class="field">
          <label>Number of Product</label>
          <form:input path="number" type="number" step="1" min="0" placeholder="Number of product" class="form-control" />
        </div>             
        
        <div class="field">
          <label>Information of Product</label>
          <form:textarea path="information" placeholder="Information of product" class="form-control" />
        </div>     
       
        <div class="field">
          <div class="field">
            <label>
              <form:checkbox path="isPromotion" value="true" class="has-sale-off" /> 
              Has sale off?
            </label>
          </div>
          
          <div class="field promotion-product" 
            style="display: ${product.getIsPromotion() ? 'block' : 'none'};">          
            <label>Sale off (%)</label>
            <form:input path="saleOff" type="number" min="1" max="99" step="any" 
              placeHolder="Sale Off of product" value="1" class="form-control" />
              
            <label>Promotion of Product</label>
            <form:select path="promotionId" value="" class="form-control">
              <c:forEach var="promotion" items="${promotions}">
                <form:option value="${promotion.getId()}" 
                  selected="${promotion.getId() == product.getPromotion().getId() ? 'selected' : ''}">
                  From ${promotion.getStartDate()} To ${promotion.getEndDate()}
                </form:option>              
              </c:forEach>
            </form:select>
          </div>
        </div>              
                     
        <div class="actions">
          <a href="${contextPath}/admin/products/${product.getId()}" class="btn btn-default btn-update-product">
            Cancel
          </a>
          
          <input type="submit" class="btn btn-primary btn-update-product" value="Save change" />
          <div class="clearfix"></div>
        </div> 
      </div>    
    </form:form>
  </div>
</div>
