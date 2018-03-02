<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<header id="header">
  <!--header_top-->
  <div class="header_top">
    <div class="container">
      <div class="row">
        <div class="col-sm-6">
          <div class="contactinfo">
            <ul class="nav nav-pills">
              <li>
                <a href="#"> <i class="fa fa-phone"></i>+84123456789</a>
              </li>

              <li>
                <a href="#">
                  <i class="fa fa-envelope"></i>nguyen.huu.tienc@framgia.com
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div class="col-sm-6">
          <div class="social-icons pull-right">
            <ul class="nav navbar-nav">
              <li><a href="#"><i class="fa fa-facebook"></i></a></li>
              <li><a href="#"><i class="fa fa-twitter"></i></a></li>
              <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
              <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
              <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--/header_top-->

  <!--header-middle-->
  <div class="header-middle">
    <div class="container">
      <div class="row">
        <div class="col-sm-4">
          <div class="logo pull-left">
            <a href="${contextPath}"><img
              src="${contextPath}/assets/images/home/logo.jpg"
              alt="" class="header-logo" /></a>
          </div>
        </div>
        <div class="col-sm-8">
          <div class="shop-menu pull-right">
            <ul class="nav navbar-nav">
              <c:choose>
                <c:when test="${currentUser != null}">
                  <li class="dropdown">
                    <a href="#" class="item-notification">
                      <i class="fa fa-bell" aria-hidden="true"></i>
                      Notifications
                      <i class="notification-size">${currentUser.getUnWatchedNotifications()}</i>                      
                    </a>
                                          
                    <ul role="menu" class="sub-menu notifications-body">
                      <c:forEach var="i" begin="0" end="${currentUser.getNotifications().size() - 1}">
                        <c:set var="index" value="${currentUser.getNotifications().size() - 1 - i}" />                      
                        <c:set var="notification" value="${currentUser.getNotifications().get(index)}" scope="page" />
                        <li class="sub-menu-item notification ${notification.isWatched() ? '' : 'unwatched'}"
                          data-id="${notification.getId()}">
                          <a href="${contextPath}/orders/${notification.getOrder().getId()}">
                            <span class="notification-content">${notification.getContent()}</span>
                          </a>
                          <div class="small notification-time">${notification.getCreatedAt()}</div>
                        </li>
                      </c:forEach>
                    </ul>
                  </li>                  
                
                  <li>
                    <a href="${contextPath}/carts" class="item-cart">
                      <i class="fa fa-shopping-cart"></i> 
                      Carts
                      <i class="cart-size">${currentUser.getCarts().size()}</i>
                    </a>
                   </li>
                   
                   <li>
                    <a href="${contextPath}/orders" class="item-order">
                      <i class="fa fa-shopping-cart" aria-hidden="true"></i> 
                      Orders
                      <i class="order-size">${currentUser.getOrders().size()}</i>                      
                    </a>
                   </li>                      
                  
                  <li class="dropdown">
                    <a href="#">Account<i class="fa fa-angle-down"></i></a>
                    <ul role="menu" class="sub-menu">
                      <li class="sub-menu-item">
                        <a href="#"><i class="fa fa-user"></i>My profile</a>
                        <hr>
                      </li>
                      
                      <li class="sub-menu-item">
                        <a href="${contextPath}/suggests"><i class="fa fa-edit"></i>My suggests</a>
                        <hr>                      
                      </li>
                      
                      <li class="sub-menu-item">
                        <form:form action="${contextPath}/sessions" method="DELETE">
                          <i class="fa fa-lock"></i>
                          <input type="submit" class="logout" value="Logout" />
                        </form:form>
                      </li>
                    </ul>
                  </li>
                </c:when>
                <c:otherwise>
                  <li>
                    <a href="${contextPath}/sessions/new"><i
                      class="fa fa-lock"></i> Login
                    </a>
                  </li>
                  
                  <li>
                    <a href="${contextPath}/registrations/new">
                      <i class="fa fa-lock"></i> Signup
                    </a>
                  </li>
                </c:otherwise>
              </c:choose>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--/header-middle-->

  <!--header-bottom-->
  <div class="header-bottom">
    <div class="container">
      <div class="row">
        <div class="col-sm-7">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle"
              data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span> <span
                class="icon-bar"></span> <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
          </div>
          <div class="mainmenu pull-left">
            <ul class="nav navbar-nav collapse navbar-collapse">
              <li>
                <a href="${contextPath}" class="${title == null ? 'active' : ''}">
                  Home
                </a>
              </li>
              <li>
                <a href="${contextPath}/products" class="${title == 'products' ? 'active' : ''}">
                  Products
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div class="col-sm-5">
          <div class="search_box pull-right">
            <input type="text" placeholder="Search" />
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--/header-bottom-->
</header>
