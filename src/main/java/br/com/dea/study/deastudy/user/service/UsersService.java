package br.com.dea.study.deastudy.user.service;

import br.com.dea.study.deastudy.exeptions.NotFoundException;
import br.com.dea.study.deastudy.user.domain.Users;
import br.com.dea.study.deastudy.user.repository.UsersRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Users> findAllUsers() {
        return this.usersRepository.findAll();
    }

    public Users findUserByEmail(String email) {
        Optional<Users> user = this.usersRepository.findByEmail(email);

        return user.orElseThrow(() -> new NotFoundException(Users.class, email));
    }

    public Users findUserByLinkedin(String linkedin) {
        Query query = entityManager.createNamedQuery("linkedinQuery");
        query.setParameter("linkedin", linkedin);
        List users = query.getResultList();

        Optional<Users> user = users.stream().findFirst();

        return user.orElseThrow(() -> new NotFoundException(Users.class, linkedin));
    }
}
