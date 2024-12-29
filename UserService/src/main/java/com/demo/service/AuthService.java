package com.demo.service;

import com.demo.entity.UserCredential;

public interface AuthService {

	String saveUser(UserCredential credential);

	String generateToken(String username, String role);


	UserCredential getUserDetailsbyUserId(int userId);

	UserCredential deleteUser(String username);

}