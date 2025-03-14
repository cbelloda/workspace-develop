package com.inetum.evaluaciones.ibk;

import com.inetum.evaluaciones.ibk.repository.Account;
import com.inetum.evaluaciones.ibk.repository.AccountRepository;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class IbkApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbkApplication.class, args);
	}


	
    @Bean
    public CommandLineRunner demo(AccountRepository repository) {

			return (args) -> {

					repository.saveAll(Arrays.asList(
Account.builder().numberAccount("123456788").state(true).idUser(1L).build(),
Account.builder().numberAccount("987654324").state(true).idUser(1L).build(),
Account.builder().numberAccount("123456789").state(true).idUser(2L).build(),
Account.builder().numberAccount("987654321").state(true).idUser(2L).build(),
Account.builder().numberAccount("123456789").state(true).idUser(3L).build(),
Account.builder().numberAccount("987654321").state(true).idUser(3L).build()

					));
			};

		}		
			
}
