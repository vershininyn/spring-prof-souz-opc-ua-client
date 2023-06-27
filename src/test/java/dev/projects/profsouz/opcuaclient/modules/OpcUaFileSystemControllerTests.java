package dev.projects.profsouz.opcuaclient.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.projects.profsouz.opcuaclient.controller.OpcUaFileSystemController;
import dev.projects.profsouz.opcuaclient.controller.OpcUaUniversalControllerAdvice;
import dev.projects.profsouz.opcuaclient.domain.XmlFilepathDTO;
import dev.projects.profsouz.opcuaclient.domain.request.XmlFilepathRequestDTO;
import dev.projects.profsouz.opcuaclient.service.OpcUaFileSystemService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.IOException;
import java.util.StringJoiner;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OpcUaFileSystemController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {
        OpcUaFileSystemController.class,
        OpcUaUniversalControllerAdvice.class
})
@ActiveProfiles(profiles = {"test"})
public class OpcUaFileSystemControllerTests {
    @Autowired
    private MockMvc mockMvc;

    private static String temporalXmlFileDirectory = "";

    @MockBean
    private OpcUaFileSystemService fsService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public static void setUpTests() {
        temporalXmlFileDirectory = System.getProperty("user.home");
    }

    @ParameterizedTest
    @ValueSource(strings = "template.xml")
    public void createXmlFileTemplate_andUseCorrectData_andCheckItIsSuccessfulHandled(String xmlFilename) throws Exception {
        String xmlFilepath = joinTemporalDirectoryAndXmlFilename(xmlFilename);

        UUID xmlUUID = UUID.randomUUID();

        XmlFilepathDTO responseDTO = XmlFilepathDTO.builder()
                .xmlUUID(xmlUUID)
                .isExists(true)
                .xmlFilepath(xmlFilepath)
                .xmlFilename(xmlFilename)
                .build();

        Mockito.when(fsService.createXmlFile(xmlFilepath)).thenReturn(responseDTO);

        XmlFilepathRequestDTO requestDTO = new XmlFilepathRequestDTO(xmlFilepath);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/opc-ua-fs-api/createXmlFile")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO));

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.xmlFilename", is(xmlFilename)))
                .andExpect(jsonPath("$.xmlFilepath", is(xmlFilepath)))
                .andExpect(jsonPath("$.xmlUUID", is(xmlUUID.toString())))
                .andExpect(jsonPath("$.isExists", is(true)));
    }

    @ParameterizedTest
    @ValueSource(strings = " ")
    public void createXmlFileTemplate_andUseIncorrectData_andCheckItIsSuccessfulHandled(String xmlFilename) throws Exception {
        String xmlFilepath = joinTemporalDirectoryAndXmlFilename(xmlFilename),
                ioExceptionMessage = "Unacceptable xml file path";

        Mockito.when(fsService.createXmlFile(xmlFilepath)).thenThrow(new IOException(ioExceptionMessage));

        XmlFilepathRequestDTO requestDTO = new XmlFilepathRequestDTO(xmlFilepath);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/opc-ua-fs-api/createXmlFile")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO));

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.statusCode", is(HttpStatus.INTERNAL_SERVER_ERROR.value())))
                .andExpect(jsonPath("$.message", is(ioExceptionMessage)))
                .andExpect(jsonPath("$.timestamp", isA(Long.class)));
    }

    @ParameterizedTest
    @ValueSource(strings = "template.xml")
    public void deleteXmlFileTemplate_andUseCorrectData_andCheckItIsSuccessfulHandled(String xmlFilename) throws Exception {
        String xmlFilepath = joinTemporalDirectoryAndXmlFilename(xmlFilename);

        UUID xmlUUID = UUID.randomUUID();

        XmlFilepathDTO responseDTO = XmlFilepathDTO.builder()
                .xmlUUID(xmlUUID)
                .isExists(true)
                .xmlFilepath(xmlFilepath)
                .xmlFilename(xmlFilename)
                .build();

        Mockito.when(fsService.createXmlFile(xmlFilepath)).thenReturn(responseDTO);

        XmlFilepathRequestDTO requestDTO = new XmlFilepathRequestDTO(xmlFilepath);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/opc-ua-fs-api/createXmlFile")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO));

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.xmlFilename", is(xmlFilename)))
                .andExpect(jsonPath("$.xmlFilepath", is(xmlFilepath)))
                .andExpect(jsonPath("$.xmlUUID", is(xmlUUID.toString())))
                .andExpect(jsonPath("$.isExists", is(true)));
    }

    @ParameterizedTest
    @ValueSource(strings = " ")
    public void createXmlFileTemplate_andUseIncorrectData_andCheckItIsSuccessfulHandled(String xmlFilename) throws Exception {
        String xmlFilepath = joinTemporalDirectoryAndXmlFilename(xmlFilename),
                ioExceptionMessage = "Unacceptable xml file path";

        Mockito.when(fsService.createXmlFile(xmlFilepath)).thenThrow(new IOException(ioExceptionMessage));

        XmlFilepathRequestDTO requestDTO = new XmlFilepathRequestDTO(xmlFilepath);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/opc-ua-fs-api/createXmlFile")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO));

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.statusCode", is(HttpStatus.INTERNAL_SERVER_ERROR.value())))
                .andExpect(jsonPath("$.message", is(ioExceptionMessage)))
                .andExpect(jsonPath("$.timestamp", isA(Long.class)));
    }

    private String joinTemporalDirectoryAndXmlFilename(String xmlFilename) {
        String fileSeparator = System.getProperty("file.separator");

        return (new StringJoiner(fileSeparator, "", ""))
                .add(temporalXmlFileDirectory)
                .add(xmlFilename)
                .toString();
    }
}
