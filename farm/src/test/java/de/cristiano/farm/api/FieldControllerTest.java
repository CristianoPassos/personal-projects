package de.cristiano.farm.api;


import de.cristiano.farm.api.dto.FieldDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static de.cristiano.farm.api.util.JsonUtil.toJson;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/insert_test_data.sql")
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:sql/delete_test_data.sql")
public class FieldControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String url = "/field";

    @Test
    public void searchField_success() throws Exception {
        FieldDTO search = FieldDTO.builder().name("Test").build();

        mvc.perform(post(url + "/search")
                .contentType(APPLICATION_JSON)
                .content(toJson(search)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("[0].name", is(("Test Field 1"))))
        ;
    }

    @Test
    public void getField_success() throws Exception {
        long valid_id = 1L;

        mvc.perform(get(url + "/" + valid_id)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("name", is(("Test Field 1"))))
        ;
    }

    @Test
    public void getField_notFound() throws Exception {
        long invalid_id = -1L;

        mvc.perform(get(url + "/" + invalid_id)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
        ;
    }

}
