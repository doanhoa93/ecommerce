<div class="item ${index == 1 ? 'active' : ''}">
  <div class="col-sm-6">
    <h1>
      <span>ECOMMERCE</span>
    </h1>
    <h2>${product.getName()}</h2>
    <p>${product.getInformation()}</p>
    <button type="button" class="btn btn-default get">Get it
      now</button>
  </div>
  <div class="col-sm-6">
    <img src="${pageContext.request.contextPath}/${product.getAvatar()}"
      class="girl img-responsive" alt="" /> <img>
  </div>
</div>
