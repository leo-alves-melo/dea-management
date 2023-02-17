package br.com.dea.study.deastudy.user.controller;

import br.com.dea.study.deastudy.user.domain.Users;
import br.com.dea.study.deastudy.user.repository.UsersRepository;
import br.com.dea.study.deastudy.user.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Tag(name = "Users", description = "The User API")
public class UsersController {

    @Autowired
    UsersService usersService;
    @Autowired
    private UsersRepository usersRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Operation(summary = "Load user by linkedin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Error fetching student list"),
    })
    @GetMapping("/linkedin")
    public ResponseEntity<Users> getFactory(@RequestParam(value = "linkedin") String linkedin) {
        Users users = usersService.findUserByLinkedin(linkedin);
        return ResponseEntity.ok(users);

    }

    @GetMapping("/users")
    public Page<Users> getStudents(@RequestParam Integer page,
                                   @RequestParam Integer pageSize) {

        Page<Users> usersPaged = this.usersService.findAllUsersPaginated(page, pageSize);
        return usersPaged;

    }
}
