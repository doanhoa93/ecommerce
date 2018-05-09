<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="category category-products">
  <div class="category-name">
    <h3>
      <a href="${contextPath}/categories/${category.getId()}" class="category-name">
        ${category.getName()} </a>
    </h3>
  </div>

  <div>Created at: ${category.getCreatedAt()}</div>
</div>
