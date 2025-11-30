package com.krishnaallu009.springBoot;

import com.krishnaallu009.springBoot.model.Author;
import com.krishnaallu009.springBoot.repository.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootAndDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAndDataJpaApplication.class, args);
	}

    /**
     * CommandLineRunner to run code at application startup
     * @param authorRepository
     * @return
     */
    @Bean
    public CommandLineRunner commandLineRunner(
            AuthorRepository authorRepository
    ) {
        return args -> {
            var author = Author.builder()
                    .firstName("Krishna")
                    .lastName("Allu")
                    .email("krishna@gmail.com")
                    .age(26)
                    .build();

            authorRepository.save(author);
        };
    }

}
