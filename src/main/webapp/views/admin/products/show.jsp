<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="admin-product">
  <div class="product-header">
    <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 product-header-left">
      <div class="product-name">
        ${product.getName()}
      </div>

      <div class="product-rate">
        <c:if test="${product.getRating() > 0}">
          <c:forEach begin="1" end="${product.getRating()}">
            <span class="rate"><i class="fa fa-star active"></i></span>
          </c:forEach>
  
          <c:forEach begin="${product.getRating() + 1}" end="5">
            <span class="rate"><i class="fa fa-star"></i></span>
          </c:forEach>
          <span>(${product.getRating()})</span>
        </c:if>
      </div>
      
      <a href="${contextPath}/admin/products/${product.getId()}/edit" 
        class="btn btn-primary edit-product">
        Edit this product
      </a>
    
      <form:form action="${contextPath}/admin/products/${product.getId()}" method="DELETE" 
        class="delete-product">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" class="btn btn-danger" value="Delete this product">
      </form:form>      
    </div>
    <div class="clearfix"></div>
  </div>
      
  <div class="product-body">
    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 product-body-left">
      <img src="${product.getAvatar()}" class="img-responsive" />
    </div>

    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 product-body-right">
      <div class="product-price">
        <fmt:setLocale value="en_US" />
        <fmt:formatNumber value="${product.getPrice()}" type="currency" />
      </div>

      <div class="product-category">
        <span class="category-title">Category: </span>
        <span class="category">${product.getCategory().getName()}</span>
      </div>

      <div class="product-number">
        <span class="number-title">Number: </span>
        <span class="number">${product.getNumber()}</span>
      </div>
      
      <c:if test="${product.getIsPromotion()}">
        <div class="product-promotion">
          <span class="promotion-title">Sale Off:</span>
          <span class="promotion">${product.getSaleOff()}%</span>
        </div>
        
        <div>
          From ${product.getPromotion().getStartDate()} To ${product.getPromotion().getEndDate()}
        </div>
      </c:if>
      
      <div class="product-information">
        <span class="information">${product.getInformation()}</span>
      </div>
    </div>
    <div class="clearfix"></div>
  </div>

  <div class="product-footer">
    <div class="product-images">
      <hr>
      <div id="recommended-item-carousel" class="carousel slide"
        data-ride="carousel">
        <c:choose>
          <c:when test="${slideSize > 0}">
            <div class="carousel-inner">
              <c:forEach begin="0" end="${slideSize - 1}" varStatus="loop">
                <div class="item ${loop.index == 0 ? 'active' : ''}">
                  <c:forEach var="image" items="${product.getImages()}" begin="${loop.index * 3}"
                    end="${loop.index * 3 + 2}">
                    <div class="col-sm-4">
                      <img class="img-responsive product-image" src="${image.getImage()}" alt="" />
                    </div>
                  </c:forEach>
                </div>
              </c:forEach>
            </div>

            <a class="left recommended-item-control"
              href="#recommended-item-carousel" data-slide="prev">
              <i class="fa fa-angle-left"></i>
            </a>
            <a class="right recommended-item-control"
              href="#recommended-item-carousel" data-slide="next">
              <i class="fa fa-angle-right"></i>
            </a>
          </c:when>

          <c:otherwise>
            <h3>No images</h3>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>
  <div class="clearfix"></div>
</div>
