<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="admin-suggest">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Suggest detail</h4>
      </div>
      <div class="modal-body">
        <div class="suggest-detail-info">
          <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
            <div class="suggest-field">
              <span>Customer: </span>
              <span class="suggest-detail-customer">${suggest.getUser().getName()}</span>
            </div>

            <div class="suggest-field">
              <span>Status: </span>
              <span class="suggest-status status-${suggest.getStatus()}">
                ${suggest.getStatus()} </span>
            </div>

            <div class="suggest-field">
              <span>Information: </span>
              <span>${suggest.getInformation()}</span>
            </div>
          </div>

          <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
            <div class="suggest-field">
              <span class="suggest-detail-avatar">
                <img src="${suggest.getAvatar()}" class="img-responsive" />
              </span>
            </div>
          </div>
        </div>
        <table class="table table-bsuggested suggest-table" style="width: 100%">
          <thead class="">
            <tr>
              <th>Product's name</th>
              <th>Product's category</th>
              <th>Product's price</th>
              <th>Created at</th>
            </tr>
          </thead>

          <tbody>
            <tr>
              <td>${suggest.getName()}</td>
              <td>${suggest.getCategory()}</td>
              <td>
                <fmt:setLocale value="en_US" />
                <fmt:formatNumber value="${suggest.getPrice()}" type="currency" />
              </td>
              <td>${suggest.getCreatedAt()}</td>
            </tr>
          </tbody>
        </table>
        <div>
          <c:set var="suggest" value="${suggest}" scope="session" />
          <c:import url="/views/admin/suggests/form.jsp" />
          <c:remove var="suggest" scope="session" />
        </div>
      </div>
    </div>
  </div>
</div>
