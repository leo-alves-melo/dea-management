package br.com.dea.study.deastudy.student.DTO;

import br.com.dea.study.deastudy.student.domain.Student;
import br.com.dea.study.deastudy.user.domain.Users;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
public class StudentDTO {

    private String name;
    private String email;
    private String linkedin;
    private String university;
    private String graduation;
    private LocalDate finishDate;

    public static StudentDTO fromStudent(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        Users user = student.getUsers();

        studentDTO.setName(user.getName());
        studentDTO.setEmail(user.getEmail());
        studentDTO.setLinkedin(user.getLinkedin());
        studentDTO.setGraduation(student.getGraduation());
        studentDTO.setUniversity(student.getUniversity());
        studentDTO.setFinishDate(student.getFinishDate());

        return studentDTO;
    }

    public static List<StudentDTO> fromStudents(List<Student> students) {
        return students.stream().map(student -> {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            return studentDTO;
        }).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }


}
