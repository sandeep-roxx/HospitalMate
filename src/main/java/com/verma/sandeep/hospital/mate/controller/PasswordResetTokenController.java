package com.verma.sandeep.hospital.mate.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verma.sandeep.hospital.mate.entity.User;
import com.verma.sandeep.hospital.mate.service.impl.PasswordResetTokenService;
import com.verma.sandeep.hospital.mate.service.impl.UserMgmtService;

@RestController
@RequestMapping("/user")
public class PasswordResetTokenController {
	
	@Autowired
	private UserMgmtService userService;
	@Autowired
	private PasswordResetTokenService resetService;
	
	//Request Password Reset (Send Email)
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
    	
       Optional<User> userOpt=userService.findByEmail(email);
       if (userOpt.isEmpty()) {
           return ResponseEntity.badRequest().body("User not found with email: " + email);
       }
       String token = resetService.generateResetToken(email);
       String resetLink = "http://localhost:4200/reset-password?token=" + token;
       //TODO : Email to send the resetLink
       
       return ResponseEntity.ok("Reset password link sent to your email.");
    	
    }
    
   // Validate Reset Token
    @GetMapping("/validate-reset-token")
    public ResponseEntity<String> validateResetToken(@RequestParam String token) {
        if (resetService.isValidToken(token)) {
            return ResponseEntity.ok("Valid token");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token,
    		                                                                                 @RequestParam String newPassword
    		                                                                                 ) 
    {
    	Optional<String> emailOpt=resetService.getEmailByToken(token);
    	
    	if (emailOpt.isPresent()) {
            userService.updatePassword(emailOpt.get(), newPassword);
            resetService.deleteToken(token);  // Remove used token
            return ResponseEntity.ok("Password reset successful.");
        }

        return ResponseEntity.badRequest().body("Invalid or expired token.");
    	
    }

}
