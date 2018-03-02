package com.framgia.bean;

import java.util.Date;

public class ChatInfo {
	private Integer id;
	private UserInfo sender;
	private UserInfo receiver;
	private Integer senderId;
	private Integer receiverId;
	private String content;
	private Date createdAt;
	private boolean watched;

	public ChatInfo() {
	}

	public ChatInfo(Integer id, String content, Date createdAt) {
		this.id = id;
		this.content = content;
		this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserInfo getSender() {
		return sender;
	}

	public void setSender(UserInfo sender) {
		this.sender = sender;
	}

	public UserInfo getReceiver() {
		return receiver;
	}

	public void setReceiver(UserInfo receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public boolean isWatched() {
		return watched;
	}

	public void setWatched(boolean watched) {
		this.watched = watched;
	}
}
