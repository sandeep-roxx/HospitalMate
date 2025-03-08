package com.verma.sandeep.hospital.mate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verma.sandeep.hospital.mate.bind.PasswordUpdateRequest;
import com.verma.sandeep.hospital.mate.bind.UserRequest;
import com.verma.sandeep.hospital.mate.bind.UserResponse;
import com.verma.sandeep.hospital.mate.service.IUserMgmtService;
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

}
