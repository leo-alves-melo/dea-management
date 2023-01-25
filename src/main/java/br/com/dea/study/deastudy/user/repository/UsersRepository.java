package br.com.dea.study.deastudy.user.repository;

import br.com.dea.study.deastudy.user.domain.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    public Optional<Users> findByEmail(String email);

    @Query("SELECT u FROM Users u WHERE name = :name")
    public Optional<Users> findByName(String name);

    @Query("SELECT u FROM Users u WHERE linkedin = :linkedin")
    public Optional<Users> findByLinkedin(String linkedin);
}
