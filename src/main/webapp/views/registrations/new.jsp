<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<section id="form">
  <div class="container">
    <div class="row">
      <div class="col-sm-4"></div>
      <div class="col-sm-4">
        <div class="signup-form">

          <h2>Sign up</h2>
          <c:if test="${message != null}">
            <div class="alert alert-warning">${message}</div>
          </c:if>

          <form:form id="new-user" action="${contextPath}/registrations" method="POST"
            modelAttribute="user" class="form-signup">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <form:input path="name" placeholder="Name" />
            <form:input path="email" placeholder="Email" />
            <form:password path="password" placeholder="Password" />
            <select name="profile.gender" form="new-user" class="form-control">
              <option value="MALE" selected>MALE</option>
              <option value="FEMALE">FEMALE</option>
            </select>
            <input type="submit" class="btn btn-primary" value="Sign up" />
          </form:form>
        </div>
      </div>
      <div class="col-sm-4"></div>
    </div>
  </div>
</section>
