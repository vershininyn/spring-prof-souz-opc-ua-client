package dev.projects.profsouz.opcuaclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public ModelAndView getDefaultData() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("index");

        return mv;
    }

}
