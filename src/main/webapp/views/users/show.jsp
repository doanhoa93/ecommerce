<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="profile" value="${user.getProfile()}" />

<div class="user">
  <div class="col-lg-4 col-md-4 col-xs-12 col-sm-12 user-left">
    <div class="field user-avatar">
      <label for="upload" class="upload-avatar">
        <input type="file" name="avatar" id="upload" style="display: none">
        <img src="${user.getAvatar()}" class="img-responsive avatar">
      </label>
    </div>

    <div class="field">
      <span>
        <b>${user.getName()}</b>
      </span>
    </div>

    <div class="field">
      <span>
        <b>${user.getEmail()}</b>
      </span>
    </div>

    <div class="field">
      <span>
        <b>Gender:</b>
      </span>
      <span>${profile.getGender()}</span>
    </div>

    <div class="field">
      <span>
        <b>Carts:</b>
      </span>
      <span>${user.getCarts().size()}</span>
    </div>

    <div class="field">
      <span>
        <b>Orders:</b>
      </span>
      <span>${user.getOrders().size()}</span>
    </div>
  </div>

  <c:if test="${currentUser.getId() == user.getId()}">
    <div class="col-lg-8 col-md-8 col-xs-12 col-sm-12 user-right">
      <h2 class="center">Edit information</h2>
      <form:form id="edit-user" action="${contextPath}/users/${currentUser.getId()}" method="PATCH"
        modelAttribute="user">
        <div class="field">
          <label>Name</label>
          <form:input path="name" class="form-control" value="${user.getName()}" />
        </div>

        <div class="field">
          <label>PhoneNumber</label>
          <input name="profile.phoneNumber" class="form-control" value="${profile.getPhoneNumber()}" />
        </div>

        <div class="field">
          <label>Address</label>
          <input name="profile.address" class="form-control" value="${profile.getAddress()}" />
        </div>

        <div class="field">
          <label>Birthday</label>
          <fmt:formatDate value="${profile.getBirthday()}" pattern="MM/dd/yyyy" var="birthday" />
          <input name="profile.birthday" class="form-control datepicker" value="${birthday}"
            data-provide="datepicker" />
        </div>

        <div class="field">
          <label>Gender</label>
          <select name="profile.gender" form="edit-user" class="gender">
            <c:choose>
              <c:when test="${profile.getGender() == genderes['male']}">
                <option value="${genderes['male']}" selected>MALE</option>
                <option value="${genderes['female']}">FEMALE</option>
              </c:when>

              <c:otherwise>
                <option value="${genderes['male']}">MALE</option>
                <option value="${genderes['female']}" selected>FEMALE</option>
              </c:otherwise>
            </c:choose>
          </select>
        </div>

        <div class="field">
          <label>Current Password</label>
          <form:password path="password" class="form-control" />
        </div>

        <div class="field">
          <label>New Password</label>
          <input name="newPassword" type="password" class="form-control" />
        </div>

        <div class="field">
          <input type="submit" value="Save" class="btn btn-primary user-save">
          <div class="clearfix"></div>
        </div>
      </form:form>
    </div>
  </c:if>
  <div class="clearfix"></div>
</div>
