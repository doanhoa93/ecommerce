<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="admin-new-product">
  <h2 class="header">New product</h2>
  <div class="admin-new-product-body">
    <form:form id="create-product" action="${contextPath}/admin/products" method="POST"
      modelAttribute="product" enctype="multipart/form-data" class="product-form">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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
            <i class="fa fa-minus remove-image" aria-hidden="true"> Remove image</i>
            <div class="product-image-form">
              <form:input type="hidden" path="imagesStatus" value="0" />
              <form:input type="hidden" path="imageIds" value="0" />
              <form:input type="file" path="imageFiles" class="image" />
              <div class="product-image"></div>
              <hr>
            </div>
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
          <form:input path="price" type="number" step="any" min="0" placeholder="Price of product"
            class="form-control" />
        </div>

        <div class="field">
          <label>Category of Product (*)</label>
          <form:select path="categoryId" class="form-control">
            <c:forEach var="category" items="${categories}">
              <form:option value="${category.getId()}">
                ${category.getName()}
              </form:option>
            </c:forEach>
          </form:select>
        </div>

        <div class="field">
          <label>Number of Product</label>
          <form:input path="number" type="number" step="1" min="0" placeholder="Number of product"
            class="form-control" />
        </div>

        <div class="field">
          <label>Information of Product</label>
          <form:textarea path="information" placeholder="Information of product"
            class="form-control" />
        </div>

        <div class="field">
          <div class="field">
            <label>
              <form:checkbox path="isPromotion" value="true" class="has-sale-off" />
              Has sale off?
            </label>
          </div>

          <div class="field promotion-product">
            <label>Sale off (%)</label>
            <form:input path="saleOff" type="number" min="1" max="99" step="any"
              placeHolder="Sale Off of product" value="1" class="form-control" />

            <label>Promotion of Product</label>
            <form:select path="promotionId" class="form-control">
              <c:forEach var="promotion" items="${promotions}">
                <form:option value="${promotion.getId()}">
                  From ${promotion.getStartDate()} To ${promotion.getEndDate()}
                </form:option>
              </c:forEach>
            </form:select>
          </div>
        </div>

        <div class="actions">
          <input type="submit" class="btn btn-primary btn-create-product" value="Create product" />
          <div class="clearfix"></div>
        </div>
      </div>
    </form:form>
  </div>
</div>
