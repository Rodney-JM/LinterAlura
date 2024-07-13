package com.jrm.literAlura.LiterAlura;

import com.jrm.literAlura.LiterAlura.principal.Main;
import com.jrm.literAlura.LiterAlura.repository.AutorRepository;
import com.jrm.literAlura.LiterAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(livroRepository, autorRepository);
		main.mostreMenu();
	}
}
