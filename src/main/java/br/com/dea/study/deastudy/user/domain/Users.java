package br.com.dea.study.deastudy.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "linkedinQuery", query = "SELECT u FROM Users u where u.linkedin = :linkedin")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String linkedin;

}
