package com.verma.sandeep.hospital.mate.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.verma.sandeep.hospital.mate.constant.UserRole;
import com.verma.sandeep.hospital.mate.entity.User;
import com.verma.sandeep.hospital.mate.service.impl.IUserMgmtService;
import com.verma.sandeep.hospital.mate.util.PasswordGeneratorUtil;

@Component
public class MasterAccountRunner implements CommandLineRunner {
	
	@Value("${master.user.name}")
	private String name;
	
	@Value("${master.user.email}")
	private String email;
	
	@Autowired
	private IUserMgmtService userService;
	
	@Autowired
	private PasswordGeneratorUtil passwordGenerator;

	@Override
	public void run(String... args) throws Exception {
		if(!userService.findByEmail(email).isPresent()) {
			User user=new User();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(passwordGenerator.generatePassword());
			user.setRole(UserRole.ADMIN.name());
			userService.saveUser(user);
			//TODO : Email pending
		}

	}

}
