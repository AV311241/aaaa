package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.entity.UserCredential;
import com.demo.repository.UserCredentialRepository;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserCredentialRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user added to the system";
    }

    public String generateToken(String username,String role) {
        return jwtService.generateToken(username,role);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

	public UserCredential getUserDetailsbyUserId(int userId) {
		return repository.findById(userId).get();
	
	}

	public UserCredential deleteUser(String username) {
		
		UserCredential user = repository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user not Found :"+username));
		repository.deleteByUsername(username);
		
		return user;
	}

}
