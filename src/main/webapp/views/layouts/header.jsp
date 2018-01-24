<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
                  <li><a href="#"><i class="fa fa-user"></i>
                      Account</a></li>
                  <li><a
                    href="${contextPath}/carts"><i
                      class="fa fa-shopping-cart"></i> Cart</a></li>
                  <li><a
                    href="${contextPath}/sessions/delete"
                    class="logout-btn"><i class="fa fa-lock"></i>Logout</a></li>
                </c:when>
                <c:otherwise>
                  <li><a
                    href="${contextPath}/sessions/new"><i
                      class="fa fa-lock"></i> Login</a></li>
                  <li><a
                    href="${contextPath}/registrations/new"><i
                      class="fa fa-lock"></i> Signup</a></li>
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
                <a href="${contextPath}" class="active">Home</a>
              </li>
              <li><a href="">Products</a></li>
              <li><a href="">Categories</a></li>
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
