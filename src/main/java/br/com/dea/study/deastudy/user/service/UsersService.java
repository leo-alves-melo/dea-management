package br.com.dea.study.deastudy.user.service;

import br.com.dea.study.deastudy.exeptions.NotFoundException;
import br.com.dea.study.deastudy.user.domain.Users;
import br.com.dea.study.deastudy.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> findAllUsers() {
        return this.usersRepository.findAll();
    }

    public Users findUserByEmail(String email) {
        Optional<Users> user = this.usersRepository.findByEmail(email);

        return user.orElseThrow(() -> new NotFoundException(Users.class, email));
    }

    public Users findUserByLinkedin(String linkedin) {
        Optional<Users> user = this.usersRepository.findByLinkedin(linkedin);

        return user.orElseThrow(() -> new NotFoundException(Users.class, linkedin));
    }
}
