package com.elca.jobfairmanagementsystem;

import com.elca.jobfairmanagementsystem.entity.User;
import com.elca.jobfairmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JobFairManagementSystemApplication {

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(JobFairManagementSystemApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner init (UserRepository userRepository){
//		return args -> {
//			User user = new User();
//			user.setVisa("ano");
//			user.setActive(true);
//			user.setPassword(passwordEncoder.encode("ano1234"));
//			userRepository.save(user);
//		};
//	}

}
