package com.framgia.model;

import java.util.Date;

public class Chat {
	private Integer id;
	private User sender;
	private User receiver;
	private String content;
	private Date createdAt;
	private boolean watched;

	public Chat() {
	}

	public Chat(Integer id, String content) {
		this.id = id;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
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

	public boolean isWatched() {
		return watched;
	}

	public void setWatched(boolean watched) {
		this.watched = watched;
	}
}
