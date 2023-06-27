package dev.projects.profsouz.opcuaclient.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.projects.profsouz.opcuaclient.domain.XmlFilepathDTO;
import dev.projects.profsouz.opcuaclient.utils.OpcUaFileSystemObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.StringJoiner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OpcUaFileSystemControllerTests {
    @Mock
    private MockMvc mockMvc;

    private String temporalXmlFileDirectory = "";

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUpTests() {
        temporalXmlFileDirectory = System.getProperty("user.home");
    }

    @ParameterizedTest
    @CsvSource(value = "template.xml")
    public void createXmlFileTemplate_andCheckItIsSuccessful(String xmlFilename) throws Exception {
        String xmlFilepath = joinTemporalDirectoryAndXmlFilename(xmlFilename);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/createXmlFile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(xmlFilepath)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    private String joinTemporalDirectoryAndXmlFilename(String xmlFilename)
    {
        String fileSeparator = System.getProperty("file.separator");

        return (new StringJoiner(fileSeparator,"",""))
                .add(temporalXmlFileDirectory)
                .add(xmlFilename)
                .toString();
    }
}
