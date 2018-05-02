<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="rate-product">
  <c:choose>
    <c:when test="${rate.getId() != null}">
      <div class="rate-form">
        <form:form id="form-rate" action="${contextPath}/rates/${rate.getId()}"
          modelAttribute="rate" method="POST">
          <div class="rating">
            <c:forEach begin="${rating['min']}" end="${rate.getRating()}">
              <i class="fa fa-star active star-large"></i>
            </c:forEach>

            <c:forEach begin="${rate.getRating() + 1}" end="${rating['max']}">
              <i class="fa fa-star star-large"></i>
            </c:forEach>
          </div>

          <input type="hidden" name="productId" value="${product.getId()}">
          <input type="hidden" name="rating" value="5">
          <div class="btn btn-default rate-cancel">Cancel</div>
          <input type="submit" class="btn btn-primary rate-save" value="Save">
        </form:form>
      </div>

      <button class="btn btn-primary rate-form-btn">Edit rate of product?</button>
    </c:when>

    <c:otherwise>
      <div class="rate-form">
        <form:form id="form-rate" action="${contextPath}/rates" modelAttribute="rate" method="POST">
          <div class="rating">
            <c:forEach begin="${rating['min']}" end="${rating['max']}">
              <i class="fa fa-star active star-large"></i>
            </c:forEach>
          </div>

          <input type="hidden" name="productId" value="${product.getId()}">
          <input type="hidden" name="rating" value="5">
          <div class="btn btn-default rate-cancel">Cancel</div>
          <input type="submit" class="btn btn-primary rate-save" value="Save">
        </form:form>
      </div>

      <button class="btn btn-primary rate-form-btn">Rate for product?</button>
    </c:otherwise>
  </c:choose>
</div>
