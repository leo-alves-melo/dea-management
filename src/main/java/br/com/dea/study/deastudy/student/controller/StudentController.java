package br.com.dea.study.deastudy.student.controller;

import br.com.dea.study.deastudy.student.DTO.StudentDTO;
import br.com.dea.study.deastudy.student.domain.Student;
import br.com.dea.study.deastudy.student.repository.StudentRepository;
import br.com.dea.study.deastudy.student.service.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping(value = "/student/all")
    public List<Student> getAllStudents() {
        return this.studentService.findAllStudent();
    }

    @GetMapping(value = "/student/all-dto")
    public List<StudentDTO> getStudentsWithOutPagination() {
        List<Student> students = this.studentService.findAllStudent();
        return StudentDTO.fromStudents(students);
    }

    @GetMapping("/student")
    public Page<StudentDTO> getStudents(@RequestParam Integer page,
                                        @RequestParam Integer pageSize) {

        Page<Student> studentsPaged = this.studentService.findAllStudentsPaginated(page, pageSize);
        Page<StudentDTO> students = studentsPaged.map(student -> StudentDTO.fromStudent(student));
        return students;

    }

    @GetMapping("/student/id")
    public StudentDTO getStudent(@RequestParam long id) {

        Student student = this.studentService.findById(id);
        StudentDTO studentDTO = StudentDTO.fromStudent(student);
        return studentDTO;

    }
}
