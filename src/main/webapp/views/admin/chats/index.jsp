<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />

<div class="admin-chats">
  <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 admin-chats-left">
    <h3>Users</h3>
    <input type="text" name="name" placeholder="Name of users" class="name-user">
    <ul class="users">
      <c:forEach var="user" items="${users}">
        <li class="user user-chat user-chat-${user.getId()}" data-id="${user.getId()}"
          data-token="${user.getToken()}">
          <img alt="" src="${user.getAvatar()}" class="img-responsive user-chat-avatar">
          <span class="user-name">${user.getName()}</span>
          <span
            class="user-new-message new-message-${user.getId()} ${user.isAdminNewMessage() ? 'user-chat-new-message' : ''}"></span>
        </li>
      </c:forEach>
    </ul>
  </div>

  <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 admin-chats-right">
    <div class="chat-panel panel panel-default">
      <div class="panel-body">
        <ul class="chat">
          <li class="right clearfix chat-item chat-item-0">
            <span class="chat-img pull-right">
              <img src="${contextPath}/assets/images/default/supervisor.png" alt="User Avatar"
                class="img-responsive img-circle" />
            </span>
            
            <div class="chat-body clearfix">
              <div class="header">
                <strong class="pull-right primary-font">Admin</strong>
                <small class="text-muted">
                  <i class="fa fa-clock-o fa-fw"></i>
                  <span class="chat-time">
                    <fmt:formatDate type="both" value="${now}" />
                  </span>
                </small>
              </div>
              <p class="chat-content">Can I help you?</p>
            </div>
          </li>
        </ul>
      </div>

      <div class="panel-footer">
        <div class="input-group">
          <input id="btn-input" type="text" class="form-control input-sm input-chat"
            placeholder="Type your message here..." />
          <span class="input-group-btn">
            <button class="btn btn-warning btn-sm btn-chat" id="btn-chat">Send</button>
          </span>
        </div>
      </div>
    </div>
  </div>

  <div class="clearfix"></div>
</div>
