package com.verma.sandeep.hospital.mate.service.impl;

import java.util.Optional;

public interface IPasswordResetTokenService {
	
	 public String generateResetToken(String email);
	 public boolean isValidToken(String token);
	 public Optional<String> getEmailByToken(String token);
	 public void deleteToken(String token);

}
