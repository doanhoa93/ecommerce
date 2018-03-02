<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="isCurrent" value="${currentUser.getId() == chat.getSender().getId()}" />

<li class="${isCurrent ? 'left' : 'right'} 
  clearfix chat-item chat-item-${chat.getId()}" data-id="${chat.getId()}">
  <span class="chat-img pull-${isCurrent ? 'left' : 'right'}"> 
    <img src="${chat.getSender().getAvatar()}" alt="User Avatar" class="img-responsive img-circle" />
  </span>
  <div class="chat-body clearfix">
    <div class="header">
      <strong class="${isCurrent ? '' : 'pull-right'} primary-font">${chat.getSender().getName()}</strong> 
      <small class="${isCurrent ? 'pull-right' : ''} text-muted"> 
        <i class="fa fa-clock-o fa-fw"></i> 
        <span class="chat-time">${chat.getCreatedAt()}</span>
      </small>
    </div>
    <p class="chat-content">
      ${chat.getContent()}
    </p>
  </div>
</li>
