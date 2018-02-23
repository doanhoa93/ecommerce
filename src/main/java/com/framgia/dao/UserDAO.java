package com.framgia.dao;

import java.util.List;

import com.framgia.model.Comment;
import com.framgia.model.Profile;
import com.framgia.model.User;

public interface UserDAO extends BaseDAO<Integer, User> {
	User findByEmail(String email);

	Profile getProfile(Integer userId);
	
	List<Comment> getComments(Integer userId);
	
	List<User> getUsers(String role);	
}
