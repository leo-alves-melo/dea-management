package br.com.dea.study.deastudy.users;

import br.com.dea.study.deastudy.user.domain.Users;
import br.com.dea.study.deastudy.user.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.http.MediaType;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class UsersTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsersRepository usersRepository;

    @BeforeEach
    void beforeEach() {
        log.info("Before each test in " + UsersTest.class.getSimpleName());
    }

    @BeforeAll
    void beforeSuiteTest() {
        log.info("Before all tests in " + UsersTest.class.getSimpleName());
    }

    @Test
    void whenRequestingAnExistentUserByLinkedin_thenReturnTheUserSuccessfully() throws Exception {
        this.usersRepository.deleteAll();
        this.createFakeUsers(10);

        Users user = this.usersRepository.findAll().get(0);

        mockMvc.perform(get("/linkedin?linkedin=" + user.getLinkedin()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.linkedin", is(user.getLinkedin())));

    }

    @Test
    void whenRequestingAnNonExistentUserByLinkedin_thenReturnTheNotFoundError() throws Exception {

        mockMvc.perform(get("/linkedin?linkedin=notALinkedin"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)));
    }

    @Test
    void whenRequestingStudentList_thenReturnListOfStudentPaginatedSuccessfully() throws Exception {
        this.usersRepository.deleteAll();
        this.createFakeUsers(100);

        mockMvc.perform(get("/users?page=0&pageSize=12"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(12)))
                .andExpect(jsonPath("$.content[0].name", is("name 0")))
                .andExpect(jsonPath("$.content[0].email", is("email 0")))
                .andExpect(jsonPath("$.content[0].linkedin", is("linkedin_0")))
                .andExpect(jsonPath("$.content[1].name", is("name 1")))
                .andExpect(jsonPath("$.content[1].email", is("email 1")))
                .andExpect(jsonPath("$.content[1].linkedin", is("linkedin_1")))
                .andExpect(jsonPath("$.content[10].name", is("name 10")))
                .andExpect(jsonPath("$.content[10].email", is("email 10")))
                .andExpect(jsonPath("$.content[10].linkedin", is("linkedin_10")))
                .andExpect(jsonPath("$.content[11].name", is("name 11")))
                .andExpect(jsonPath("$.content[11].email", is("email 11")))
                .andExpect(jsonPath("$.content[11].linkedin", is("linkedin_11")));

    }

    @Test
    void whenRequestingUserListAndPageQueryParamIsInvalid_thenReturnBadRequestError() throws Exception {
        mockMvc.perform(get("/users?page=xx&pageSize=4"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)));
    }

    @Test
    void whenRequestingUserListAndPageQueryParamIsMissing_thenReturnBadRequestError() throws Exception {
        mockMvc.perform(get("/users?pageSize=4"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)));
    }

    @Test
    void whenRequestingUserListAndPageSizeQueryParamIsInvalid_thenReturnBadRequestError() throws Exception {
        mockMvc.perform(get("/users?pageSize=xx&page=4"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)));
    }

    @Test
    void whenRequestingUserListAndPageSizeQueryParamIsMissing_thenReturnBadRequestError() throws Exception {
        mockMvc.perform(get("/users?page=0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)));
    }

    private void createFakeUsers(int amount) {
        for (int i = 0; i < amount; i++) {
            Users user = new Users();
            user.setEmail("email " + i);
            user.setName("name " + i);
            user.setLinkedin("linkedin_" + i);
            user.setPassword("pwd " + i);

            this.usersRepository.save(user);
        }
        //List<Users> s = this.usersRepository.findAll();
    }
}
