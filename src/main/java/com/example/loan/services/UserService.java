package com.example.loan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.loan.entities.User;
import com.example.loan.pojo.UserPojo;
import com.example.loan.repository.UserRepository;
import com.example.loan.utils.ResponseMessageUtil;
import com.example.loan.utils.RoleUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public String createUser(UserPojo userPojo) {
		User user = null;
		try {
			user = userRepository.findByEmail(userPojo.getEmail());
			if (user == null) {
				user = new User(userPojo);
				user.setRole(RoleUtil.CUSTOMER);
				userRepository.save(user);
				return ResponseMessageUtil.CUSTOMER_REGISTERED_SUCCESSFULLY;
			} else {
				return ResponseMessageUtil.CUSTOMER_ALREADY_EXIST;
			}
		} catch (Exception e) {
			return ResponseMessageUtil.SOMETHING_WENT_WRONG;
		}
	}

	public ResponseEntity<?> loginUser(UserPojo userPojo) {
		User user = null;
		UserPojo responsePojo = null;
		try {
			user = userRepository.findByEmail(userPojo.getEmail());
			if (user == null) {
				return ResponseEntity.ok(ResponseMessageUtil.CUSTOMER_NOT_FOUND); 
			} else {
				if (user.getPassword().contentEquals(userPojo.getPassword())) {
					responsePojo = new UserPojo(user);
					return ResponseEntity.ok(responsePojo);
				} else {
					return ResponseEntity.ok(ResponseMessageUtil.WRONG_PASSWORD);
				}
			}
		} catch (Exception e) {
			return ResponseEntity.ok(ResponseMessageUtil.SOMETHING_WENT_WRONG);
		}
	}

}
