package com.verma.sandeep.hospital.mate.bind;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordUpdateRequest {
	
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
	
	@NotBlank(message = "Old password is required")
    private String oldPassword;
	
    @NotBlank(message = "New password is required")
    private String newPassword;
    
    @NotBlank(message = "Re-enter new password is required")
    private String reenterNewPassword;

}
