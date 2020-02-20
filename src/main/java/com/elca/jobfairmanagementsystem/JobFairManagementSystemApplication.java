package com.elca.jobfairmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
//			user.setVisa("abh");
//			user.setActive(true);
//			user.setPassword(passwordEncoder.encode("abh1234"));
//			userRepository.save(user);
//
//			User user1 = new User();
//			user1.setVisa("ano");
//			user1.setActive(true);
//			user1.setPassword(passwordEncoder.encode("ano1234"));
//			userRepository.save(user1);
//		};
//	}

}
