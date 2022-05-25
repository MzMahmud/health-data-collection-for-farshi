package com.moazmahmud.spring_boot_thymeleaf;

import com.moazmahmud.spring_boot_thymeleaf.common.classes.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class RootController extends BaseController {
    @GetMapping
    public String getRootPage(){
        return "redirect:/health-data";
    }
}
