<div class="navbar-default sidebar" role="navigation">
  <div class="sidebar-nav navbar-collapse">
    <ul class="nav" id="side-menu">
      <li class="sidebar-search">
        <div class="input-group custom-search-form">
          <input type="text" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
            <button class="btn btn-default" type="button">
              <i class="fa fa-search"></i>
            </button>
          </span>
        </div>
      </li>

      <li>
        <a href="${contextPath}/admin/orders">
          <i class="fa fa-shopping-cart fa-fw"></i>
          Orders
          <span class="new-order-item-icon"></span>
        </a>
      </li>

      <li>
        <a href="#">
          <i class="fa fa-product-hunt fa-fw" aria-hidden="true"></i>
          Products
          <span class="fa arrow"></span>
        </a>

        <ul class="nav nav-second-level">
          <li>
            <a href="${contextPath}/admin/products">All products</a>
          </li>
          <li>
            <a href="${contextPath}/admin/products/new" class="new-product">New product</a>
          </li>
        </ul>
      </li>

      <li>
        <a href="#">
          <i class="fa fa-tasks fa-fw"></i>
          Categories
          <span class="fa arrow"></span>
        </a>

        <ul class="nav nav-second-level">
          <li>
            <a href="${contextPath}/admin/categories">All categories</a>
          </li>
          <li>
            <a data-href="${contextPath}/admin/categories/new" class="new-category">New category</a>
          </li>
        </ul>
      </li>

      <li>
        <a href="${contextPath}/admin/promotions">
          <i class="fa fa-gift fa-fw"></i>
          Promotions
        </a>
      </li>

      <li>
        <a href="${contextPath}/admin/suggests">
          <i class="fa fa-edit fa-fw"></i>
          Suggests
        </a>
      </li>

      <li>
        <a href="${contextPath}/admin/users">
          <i class="fa fa-user fa-fw"></i>
          Users
        </a>
      </li>

      <li class="chat-sidebar">
        <a href="${contextPath}/admin/chats">
          <i class="fa fa-comments" aria-hidden="true"></i>
          Chats
          <span class="new-message ${currentUser.isNewMessage() ? 'user-chat-new-message' : ''}"></span>
        </a>
      </li>
    </ul>
  </div>
</div>
