<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-default navbar-static-top" role="navigation"
  style="margin-bottom: 0">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse"
      data-target=".navbar-collapse">
      <span class="sr-only">Toggle navigation</span> <span
        class="icon-bar"></span> <span class="icon-bar"></span> <span
        class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="${contextPath}/admin">Ecommerce</a>
  </div>

  <ul class="nav navbar-top-links navbar-right">
    <c:import url="/views/admin/layouts/message.jsp" />

    <c:import url="/views/admin/layouts/notification.jsp" />

  
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown"> 
        <i class="fa fa-user fa-fw"></i>
        <i class="fa fa-caret-down"></i>
      </a>
    
      <ul class="dropdown-menu dropdown-user">
        <li>
          <a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
        </li>
        
        <li>
          <a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
        </li>
        <li class="divider"></li>
        
        <li>
          <a href="${contextPath}/sessions/delete">
            <i class="fa fa-sign-out fa-fw"></i> Logout
          </a>
        </li>
      </ul>
    </li>
  </ul>

  <c:import url="/views/admin/layouts/sidebar.jsp"></c:import>
</nav>
