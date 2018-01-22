<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="product-image-wrapper">
  <div class="single-products">
    <div class="productinfo text-center">
      <img class="product-image"
        src="${pageContext.request.contextPath}/${product.getAvatar()}"
        alt="" />
      <h2>$${product.getPrice()}</h2>
      <p>${product.getName()}</p>
      <form:form
        action="${pageContext.request.contextPath}/products/${product.getId()}/carts/create"
        method="POST" modelAttribute="cart">
        <input name="quantity" type="hidden" value="1" />
        <button class="btn btn-default add-to-cart" type="submit">
          <i class="fa fa-shopping-cart"></i>Add to cart
        </button>
      </form:form>
    </div>
  </div>
</div>
