<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="order">
  <div class="order-detail-info">
    <div class="order-field">
      <span>Total Price: </span>
      <span class="order-detail-price">
        <fmt:setLocale value="en_US" />
        <fmt:formatNumber value="${order.getTotalPrice()}" type="currency" />
      </span>              
    </div>
    
    <div class="order-field">
      <span>Status: </span>
      <span class="order-status status-${order.getStatus()}">
        ${order.getStatus()}
      </span>              
    </div>
    
    <c:if test="${order.getStatus() == 'WAITING' || order.getStatus() == 'REJECT'}">
      <div class="order-field">
        <a href="${contextPath}/orders/${order.getId()}/edit" class="btn btn-primary btn-order-edit">
          Edit this order
        </a>
      </div>   
      
     <div class="order-field">
       <form:form action="${contextPath}/orders/${order.getId()}" method="DELETE">
        <input type="submit" class="btn btn-danger btn-order-delete" value="Delete this order" />
       </form:form>
      </div>                   
    </c:if>
  </div>
  <table class="table table-bordered order-table" style="width: 100%">
    <thead class="">
      <tr>
        <th>#</th>
        <th>Product's avatar</th>
        <th>Product's name</th>
        <th>Product's number</th>
        <th>Quantity</th>
        <th>Product's price</th>
        <th>Sum price</th>
        <th>Status</th>            
      </tr>
    </thead>
  
    <tbody>
      <c:forEach items="${orderProducts}" var="orderProduct" varStatus="loop">
        <c:set var="product" value="${orderProduct.getProduct()}" scope="page" />
        <tr>
          <td>${loop.index + 1}</td>
          <td>
            <a href="${contextPath}/products/${product.getId()}">
              <img src="${product.getAvatar()}" class="img-responsive order-avatar" />
            </a>
          </td>
          <td><a href="${contextPath}/products/${product.getId()}">${product.getName()}</a></td>
          <td>${product.getNumber()}</td>
          <td>${orderProduct.getQuantity()}</td>
          <td>
            <fmt:setLocale value="en_US" />
            <fmt:formatNumber value="${product.getPrice()}" type="currency" />        
          </td>
          <td>
            <fmt:setLocale value="en_US" />
            <fmt:formatNumber value="${product.getPrice() * orderProduct.getQuantity()}" 
              type="currency" />              
          </td>
          <td class="order-product-status">
            <strong class="order-product-status status-${orderProduct.getStatus()}">
              ${orderProduct.getStatus()}
            </strong>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>      
</div>

