package dev.projects.profsouz.opcuaclient.modules;

import dev.projects.profsouz.opcuaclient.controller.IndexController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class IndexControllerModulesTests {
    @Autowired
    private IndexController usersController;

    @Test
    public void getIndexPageTest() throws Exception {
        ModelAndView modelAndView = usersController.getDefaultData();

        assertTrue(modelAndView.getStatus().is2xxSuccessful());
    }
}
