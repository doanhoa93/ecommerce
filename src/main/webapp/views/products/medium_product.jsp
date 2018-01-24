<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="item ${index == 1 ? 'active' : ''}">
  <div class="col-sm-6">
    <h1>
      <span>ECOMMERCE</span>
    </h1>
    <h2>${product.getName()}</h2>
    <p>${product.getInformation()}</p>
    <form:form
      action="${contextPath}/products/${product.getId()}/carts"
      method="POST" modelAttribute="cartInfo">
      <button type="submit" class="btn btn-default get">
        Get it now
      </button>                    
    </form:form>                    
  </div>
  <div class="col-sm-6">
    <img src="${contextPath}/${product.getAvatar()}"
      class="girl img-responsive" alt="" />
  </div>
</div>
