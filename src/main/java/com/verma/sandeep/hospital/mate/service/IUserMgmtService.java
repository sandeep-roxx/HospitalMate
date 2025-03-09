package com.verma.sandeep.hospital.mate.service;

import java.util.Optional;

import com.verma.sandeep.hospital.mate.bind.PasswordUpdateRequest;
import com.verma.sandeep.hospital.mate.entity.User;

public interface IUserMgmtService {
	
	public Long saveUser(User user);
	public Optional<User> findByEmail(String email);
	public String updatePassword(PasswordUpdateRequest request);
	public void updatePassword(String email, String newPassword);
	public User getUserProfile(String email);

}
