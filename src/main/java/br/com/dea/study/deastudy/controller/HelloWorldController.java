package br.com.dea.study.deastudy.controller;

import br.com.dea.study.deastudy.user.domain.Users;
import br.com.dea.study.deastudy.user.repository.UsersRepository;
import br.com.dea.study.deastudy.user.service.UsersService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class HelloWorldController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/hello-world", method = RequestMethod.GET)
    public ResponseEntity<String> getFactory() {
        return ResponseEntity.ok("hello world, Leo!");
    }

    @RequestMapping(value = "/linkedin", method = RequestMethod.GET, params = {"linkedin"})
    public ResponseEntity<String> getFactory(@RequestParam(value = "linkedin") String linkedin) {

        try {
            Users users = usersService.findUserByLinkedin(linkedin);
            return ResponseEntity.ok("I found this user: " + users.getName());
        } catch (Error error) {
            return ResponseEntity.ok("Not found any user");
        }

    }
}

