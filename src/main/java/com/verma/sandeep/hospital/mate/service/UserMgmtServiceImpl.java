package com.verma.sandeep.hospital.mate.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.bind.PasswordUpdateRequest;
import com.verma.sandeep.hospital.mate.entity.User;
import com.verma.sandeep.hospital.mate.exception.UserNotFoundException;
import com.verma.sandeep.hospital.mate.repository.UserRepository;

@Service
public class UserMgmtServiceImpl implements IUserMgmtService,UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public Long saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user).getId();
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not exist"));
		return new org.springframework.security.core.userdetails.User(
				email,
				user.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
				);
	}

	@Override
	public String updatePassword(PasswordUpdateRequest request) {
		
		User user = userRepo.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            return "User not found";
        }
        
       // Check if the old password is correct
        if (!encoder.matches(request.getOldPassword(), user.getPassword())) {
            return "Old password is incorrect";
        }
        
        // Encode and update the new password
        user.setPassword(encoder.encode(request.getNewPassword()));
        userRepo.save(user);
        // TODO : Email pending

        return "Password updated successfully!";
	}

	@Override
	public void updatePassword(String email, String newPassword) {
		Optional<User> userOpt=userRepo.findByEmail(email);
		if(userOpt.isPresent()) {
			User user=userOpt.get();
			user.setPassword(encoder.encode(newPassword));
			userRepo.save(user);
		}	
	}

	@Override
	public User getUserProfile(String email) {
		return userRepo.findByEmail(email)
				                         .orElseThrow(()->new UserNotFoundException("User not found"));
	}

}
