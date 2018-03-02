<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="chat-panel panel panel-default">
  <div class="panel-heading chat-title">
    <i class="fa fa-comments fa-fw"></i> Chat
    <div class="btn-group pull-right">
      <button type="button" class="btn btn-default btn-xs dropdown-toggle buttom-down"
        data-toggle="dropdown">
        <i class="fa fa-chevron-down"></i>
      </button>
    </div>
  </div>
  
  <div class="panel-body">
    <ul class="chat" data-id="">
      <c:forEach var="chat" items="${chats}" varStatus="loop">
        <c:set var="chat" value="${chats.get(chats.size() - loop.index - 1)}" scope="session" />
        <c:import url="/views/chats/chat.jsp" />
        <c:remove var="chat" scope="session" />
      </c:forEach>
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
