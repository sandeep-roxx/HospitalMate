package com.verma.sandeep.hospital.mate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verma.sandeep.hospital.mate.entity.PasswordResetToken;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	
	public Optional<PasswordResetToken> findByToken(String token);
	public void deleteByEmail(String email);

}
