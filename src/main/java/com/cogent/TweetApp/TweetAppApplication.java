package com.cogent.TweetApp;

import com.cogent.TweetApp.entity.Role;
import com.cogent.TweetApp.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class TweetAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner initRoles(RoleRepository roleRepository) {
		return args -> {
			if (!roleRepository.existsByName("ROLE_USER")) {
				Role userRole = new Role();
				userRole.setName("ROLE_USER");
				roleRepository.save(userRole);
			}
		};
    }

}
