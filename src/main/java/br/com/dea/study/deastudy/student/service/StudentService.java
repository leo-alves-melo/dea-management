package br.com.dea.study.deastudy.student.service;

import br.com.dea.study.deastudy.exeptions.NotFoundException;
import br.com.dea.study.deastudy.student.domain.Student;
import br.com.dea.study.deastudy.student.repository.StudentRepository;
import br.com.dea.study.deastudy.user.domain.Users;
import br.com.dea.study.deastudy.user.repository.UsersRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Student> findAllStudent() {
        return this.studentRepository.findAll();
    }

    public Page<Student> findAllStudentsPaginated(Integer page, Integer pageSize) {
        return this.studentRepository.findAllPaginated(PageRequest.of(page, pageSize));
    }

    public Student findById(long id) {
        return this.studentRepository.findById(id).orElseThrow(() -> new NotFoundException(Student.class, id));
    }
}
