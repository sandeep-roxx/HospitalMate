package com.verma.sandeep.hospital.mate.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verma.sandeep.hospital.mate.dto.PasswordUpdateRequest;
import com.verma.sandeep.hospital.mate.dto.UserRequest;
import com.verma.sandeep.hospital.mate.dto.UserResponse;
import com.verma.sandeep.hospital.mate.entity.User;
import com.verma.sandeep.hospital.mate.service.impl.IUserMgmtService;
import com.verma.sandeep.hospital.mate.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserMgmtService userService;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> login(@RequestBody UserRequest request){
		UsernamePasswordAuthenticationToken token=
				               new UsernamePasswordAuthenticationToken(request.getEmail(),
				            		                                                                                   request.getPassword());
		authManager.authenticate(token);
		String jwtToken=jwtUtil.generateToken(request.getEmail());
		return new ResponseEntity<UserResponse>(new UserResponse(jwtToken,"Success! Generated By Sandeep Verma"),
                                                                                                 HttpStatus.OK
                                                                                               );
	}
	
	 @PutMapping("/update-password")
	    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequest request) {
		 String response = userService.updatePassword(request);
	        return ResponseEntity.ok(response);
	 }
	 
	 @GetMapping("/profile")
	    public ResponseEntity<User> getUserProfile(Principal principal) {
	        String email = principal.getName();
	        User userProfile = userService.getUserProfile(email);
	        return ResponseEntity.ok(userProfile);
	    }

}
