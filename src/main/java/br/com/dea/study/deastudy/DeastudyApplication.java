package br.com.dea.study.deastudy;

import br.com.dea.study.deastudy.controller.HelloWorldController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.dea.study.deastudy.user.domain.Users;
import br.com.dea.study.deastudy.user.repository.UsersRepository;
import br.com.dea.study.deastudy.user.service.UsersService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class DeastudyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DeastudyApplication.class, args);
	}

	@Autowired
	private UsersService usersService;

	@Autowired
	private UsersRepository usersRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void run(String... args) throws Exception {
		Users newUser = new Users();

		newUser.setName("Leonardo");
		newUser.setEmail("leo@leo.com");
		newUser.setLinkedin("leo-alves-melo");
		newUser.setPassword("admin");

		//this.usersRepository.save(newUser);
	}

}
