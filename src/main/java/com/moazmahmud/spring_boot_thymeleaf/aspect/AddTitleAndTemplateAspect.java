package com.moazmahmud.spring_boot_thymeleaf.aspect;

import com.moazmahmud.spring_boot_thymeleaf.common.annotations.TitleAndTemplate;
import com.moazmahmud.spring_boot_thymeleaf.utils.LoggedInUserUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Stream;

@Aspect
@Component
public class AddTitleAndTemplateAspect {

    @Before("@annotation(titleAndTemplate)")
    public void addTitleAndTemplate(JoinPoint joinPoint, TitleAndTemplate titleAndTemplate) {
        Model model =
                (Model) Stream.of(joinPoint.getArgs())
                              .filter(arg -> arg instanceof Model && !(arg instanceof RedirectAttributes))
                              .findFirst()
                              .orElseThrow(() -> new IllegalArgumentException("TitleAndTemplate needs a Model argument"));
        model.addAttribute("title", titleAndTemplate.title());
        model.addAttribute("template", titleAndTemplate.template());
        model.addAttribute("loggedInUsername", LoggedInUserUtil.getUsername().orElse(""));
    }
}
