package br.com.dea.study.deastudy.user.controller;

import br.com.dea.study.deastudy.user.domain.Users;
import br.com.dea.study.deastudy.user.repository.UsersRepository;
import br.com.dea.study.deastudy.user.service.UsersService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    @Autowired
    UsersService usersService;
    @Autowired
    private UsersRepository usersRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/linkedin", method = RequestMethod.GET, params = {"linkedin"})
    public ResponseEntity<String> getFactory(@RequestParam(value = "linkedin") String linkedin) {

        try {
            Users users = usersService.findUserByLinkedin(linkedin);
            return ResponseEntity.ok("I found this user: " + users.getName());
        } catch (Error error) {
            return ResponseEntity.ok("Not found any user");
        }

    }

    @GetMapping("/users")
    public Page<Users> getStudents(@RequestParam Integer page,
                                   @RequestParam Integer pageSize) {

        Page<Users> usersPaged = this.usersService.findAllUsersPaginated(page, pageSize);
        return usersPaged;

    }
}
