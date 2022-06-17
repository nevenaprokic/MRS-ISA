package com.booking.ISAbackend.student3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class IntegrationTests {

    private static final String URL_PREFIX_MARK = "/mark";
    private static final String URL_PREFIX_SHIP = "/ship";
    private static final String URL_PREFIX_RESERVATION = "/reservation";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void getNotReviewedMarsTest() throws Exception {
            mockMvc.perform(get(URL_PREFIX_MARK + "/all-unchecked"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(3)))
                    .andExpect(jsonPath("$").exists())
                    .andExpect(jsonPath("$.[*].id").value(hasItem(1)))
                    .andExpect(jsonPath("$.[*].approved").value(hasItem(false)))
                    .andExpect(jsonPath("$.[*].comment").value(hasItem("Odlican ambijent!")))
                    .andExpect(jsonPath("$.[*].mark").value(hasItem(5)))
                    .andExpect(jsonPath("$.[*].sendingTime").value(hasItem("09/05/2022")));

    }

}


