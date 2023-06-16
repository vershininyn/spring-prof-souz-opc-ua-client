package dev.projects.profsouz.opcuaclient;

import dev.projects.profsouz.opcuaclient.controller.IndexController;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMockMvc
public class OpcUaIndexControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IndexController indexController;

    @Test
    public void getIndex_IsOk() {
        /* response = indexController.getIndex();

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals("TEXT_HTML_VALUE", response.getHeaders().getFirst("ACCEPT"));*/
    }

}
