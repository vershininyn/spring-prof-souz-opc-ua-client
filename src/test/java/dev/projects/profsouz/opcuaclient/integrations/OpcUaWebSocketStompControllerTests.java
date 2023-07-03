package dev.projects.profsouz.opcuaclient.integrations;

import dev.projects.profsouz.opcuaclient.controller.OpcUaStompClientController;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
public class OpcUaWebSocketStompControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OpcUaStompClientController opcUaStompController;

    @ParameterizedTest
    @ValueSource(strings = {"172.10.15.11", "8081"})
    public void connectToASNeGServer_usingCorrectData_andCorrectHandleIt(String host, String port) {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/opc-ua-ws-api/connectToASNeGServer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO));

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(4)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.xmlFilename", is(xmlTemplateFilename)))
                .andExpect(jsonPath("$.xmlFilepath", is(xmlFilepath)))
                .andExpect(jsonPath("$.isExists", is(true)));
    }



}
