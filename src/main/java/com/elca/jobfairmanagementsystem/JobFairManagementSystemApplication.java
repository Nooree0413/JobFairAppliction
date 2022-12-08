package com.elca.jobfairmanagementsystem;

import com.elca.jobfairmanagementsystem.entity.Role;
import com.elca.jobfairmanagementsystem.entity.User;
import com.elca.jobfairmanagementsystem.entity.UserRole;
import com.elca.jobfairmanagementsystem.repository.RoleRepository;
import com.elca.jobfairmanagementsystem.repository.UserRepository;
import com.elca.jobfairmanagementsystem.repository.UserRoleRepository;
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
//	public CommandLineRunner init (UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository){
//		return args -> {
//			User user = new User();
//			user.setVisa("mal");
//			user.setActive(true);
//			user.setPassword(passwordEncoder.encode("mal"));
//			User savedUser = userRepository.save(user);
//
//			Role role = new Role();
//			role.setVenueJobBo(true);
//			role.setCandidateAdd(true);
//			role.setCandidateDetail(true);
//			role.setCandidateList(true);
//			role.setDashboard(true);
//			role.setDescription("HR");
//			role.setHome(true);
//			role.setJobBo(true);
//			role.setJobList(true);
//			role.setName("HR");
//			role.setSkillBo(true);
//			role.setVenueBo(true);
//			role.setVenuePage(true);
//			Role savedRole = roleRepository.save(role);
//
//			UserRole userRole = new UserRole();
//			userRole.setRole(savedRole);
//			userRole.setUser(savedUser);
//			userRoleRepository.save(userRole);
//		};
//	}
}
