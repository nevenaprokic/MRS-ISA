package com.booking.ISAbackend.student1;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import com.booking.ISAbackend.config.WebConfig;
import com.booking.ISAbackend.controller.CottageController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@SpringIntegrationTest
@ContextConfiguration(classes = WebConfig.class)
public class CottageControllerTest {

    private static final String URL_PREFIX = "http://localhost:8081/cottage";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                //.apply(springSecurity())
                .build();
    }

    @MockBean
    CottageController cottageController;

    /*
     Spring pruža matcher metode u klasi MockMvcResultMatchers koje proveravaju status,
     tip sadržaja i sadržaj. Sadržaj su u ovom primeru JSON objekti.
    Sadržaj JSON objekata se proverava jsonPath matcher metodom koja
    prima JSONPath izraz. JSONPath je jezik za opis sadržaja JSON objekta. Omogućuje selekciju
    delova JSON objekta i ima istu ulogu kao XPath za XML.
    http://goessner.net/articles/JsonPath/
     */
    @Test
    public void cottageInfoTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(URL_PREFIX + "/get-info?idCottage=1"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getCottagesTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(URL_PREFIX + "/get-all"))
                .andExpect(status().isOk()).andReturn();
    }
}
