<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<section id="form">
  <div class="container">
    <div class="row">
      <div class="col-sm-4"></div>
      <div class="col-sm-4">
        <div class="login-form">

          <h2>Login to your account</h2>
          <c:if test="${message != null}">
            <div class="alert alert-warning">${message}</div>
          </c:if>

          <form:form
            action="${contextPath}/sessions"
            method="POST" modelAttribute="userInfo" class="form-login">
            <form:input path="email" placeholder="Email" />
            <form:password path="password" placeholder="Password" />
            <label> <form:checkbox path="remember" value="true"
                class="checkbox-remember" /> <span
              class="remember-text">Keep me signed in</span>
            </label>
            <input type="submit" class="btn btn-primary" value="Login" />
          </form:form>
        </div>
      </div>
      <div class="col-sm-4"></div>
    </div>
  </div>
</section>
