package br.com.dea.study.deastudy.student.repository;

import br.com.dea.study.deastudy.student.domain.Student;
import br.com.dea.study.deastudy.user.domain.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Optional<Student> findByUniversity(String university);

    @Query("SELECT u FROM Student u WHERE graduation = :graduation")
    public Optional<Student> findByGraduation(String graduation);

    @Query("SELECT u FROM Student u WHERE id = :id")
    public Optional<Student> findById(long id);

    @Query("SELECT s FROM Student s")
    public Page<Student> findAllPaginated(Pageable pageable);
}
