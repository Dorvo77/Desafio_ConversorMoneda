package com.aluracurso.apiBook;

import com.aluracurso.apiBook.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
	public class ApiBookApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraMenu();
	}
}
