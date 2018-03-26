<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="item ${index == 1 ? 'active' : ''}">
  <div class="col-sm-6">
    <h1>
      <span>ECOMMERCE</span>
    </h1>
    <h2>
      <a href="${contextPath}/products/${product.getId()}" class="product-name">
        ${product.getName()}
      </a>
    </h2>
    <p>${product.getInformation()}</p>
    <form:form action="${contextPath}/carts" method="POST" modelAttribute="cartInfo">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <input type="hidden" name="productId" value="${product.getId()}" />      
      <button type="submit" class="btn btn-default get">
        Get it now
      </button>                    
    </form:form>                    
  </div>
  <div class="col-sm-6">
    <img src="${product.getAvatar()}"
      class="girl img-responsive" alt="" />
  </div>
</div>
