package br.com.dea.study.deastudy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
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
@OpenAPIDefinition(
		info = @Info(title = "Dea Management", version = "1.0", description = "Dea Management API Description"),
		servers = {
				@Server(url = "http://localhost:8082/swagger", description = "Local environment URL"),
				@Server(url = "https://deamanagement.com.br${server.servlet.contextPath}", description = "Development environment URL")
		}
)
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
