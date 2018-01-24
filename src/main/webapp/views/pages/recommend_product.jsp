<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="recommended_items">
  <!--recommended_items-->
  <h2 class="title text-center">Recommended Products</h2>
  <div id="recommended-item-carousel" class="carousel slide"
    data-ride="carousel">
    <div class="carousel-inner">
      <div class="item active">
        <c:forEach var="product" items="${params.recommendProducts}" begin="0" end="2">
          <div class="col-sm-4">
            <div class="product-image-wrapper">
              <div class="single-products">
                <div class="productinfo text-center">
                  <img class="product-image"
                    src="${contextPath}/${product.getAvatar()}"
                    alt="" />
                  <h2>
                    <fmt:setLocale value="en_US" />
                    <fmt:formatNumber value="${product.getPrice()}" type="currency" />                    
                  </h2>
                  <p>${product.getName()}</p>
                  <form:form
                    action="${contextPath}/products/${product.getId()}/carts"
                    method="POST" modelAttribute="cartInfo">
                    <button class="btn btn-default add-to-cart"
                      type="submit">
                      <i class="fa fa-shopping-cart"></i>Add to cart
                    </button>
                  </form:form>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>

      <div class="item">
        <c:forEach var="product" items="${params.recommendProducts}" begin="3" end="5">
          <div class="col-sm-4">
            <div class="product-image-wrapper">
              <div class="single-products">
                <div class="productinfo text-center">
                  <img class="product-image"
                    src="${contextPath}/${product.getAvatar()}"
                    alt="" />
                  <h2>$${product.getPrice()}</h2>
                  <p>${product.getName()}</p>
                  <form:form
                    action="${contextPath}/products/${product.getId()}/carts"
                    method="POST" modelAttribute="cartInfo">
                    <button class="btn btn-default add-to-cart"
                      type="submit">
                      <i class="fa fa-shopping-cart"></i>Add to cart
                    </button>
                  </form:form>
                </div>
              </div>
            </div>

          </div>
        </c:forEach>
      </div>
    </div>

    <a class="left recommended-item-control"
      href="#recommended-item-carousel" data-slide="prev"> <i
      class="fa fa-angle-left"></i>
    </a> <a class="right recommended-item-control"
      href="#recommended-item-carousel" data-slide="next"> <i
      class="fa fa-angle-right"></i>
    </a>
  </div>
</div>
