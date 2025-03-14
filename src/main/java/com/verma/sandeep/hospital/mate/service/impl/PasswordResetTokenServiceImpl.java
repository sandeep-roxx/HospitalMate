package com.verma.sandeep.hospital.mate.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.entity.PasswordResetToken;
import com.verma.sandeep.hospital.mate.repository.PasswordResetTokenRepository;

@Service
public class PasswordResetTokenServiceImpl implements IPasswordResetTokenService {
	
	@Autowired
	private PasswordResetTokenRepository tokenRepo;

	@Override
	public String generateResetToken(String email) {
		String token=UUID.randomUUID().toString();
		Date expiryDate=new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(15));
		// Delete existing token for the email if exists
		tokenRepo.deleteByEmail(email);
		PasswordResetToken resetToken=new PasswordResetToken(token, email, expiryDate);
		tokenRepo.save(resetToken);
		return token;
	}

	// Validate token (check expiry & existence)
	@Override
	public boolean isValidToken(String token) {
		Optional<PasswordResetToken>  resetToken=tokenRepo.findByToken(token);
		return resetToken.isPresent() && resetToken.get().getExpiryDate().after(new Date());
	}

	// Get email by token
	@Override
	public Optional<String> getEmailByToken(String token) {
		return tokenRepo.findByToken(token).map(resetToken->resetToken.getEmail());
	}

	// Remove token after reset
	@Override
	public void deleteToken(String token) {
		tokenRepo.findByToken(token).ifPresent(restToken->tokenRepo.delete(restToken));

	}

}
