package com.moazmahmud.spring_boot_thymeleaf.common.classes;

import com.moazmahmud.spring_boot_thymeleaf.annotations.TitleAndTemplate;
import com.moazmahmud.spring_boot_thymeleaf.common.exceptions.BadRequestException;
import com.moazmahmud.spring_boot_thymeleaf.common.exceptions.NotFoundException;
import com.moazmahmud.spring_boot_thymeleaf.common.exceptions.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@Slf4j
public abstract class BaseController {
    protected String layout = "layout/layout";

    @ExceptionHandler({BadRequestException.class})
    @TitleAndTemplate(title = "400", template = "400-bad-request")
    public String handleBadRequestException(Model model, BadRequestException exception) {
        log.error(exception.getMessage());
        model.addAttribute("errorMessage", exception.getMessage());
        return layout;
    }

    @ExceptionHandler({NotFoundException.class, NoSuchElementException.class})
    @TitleAndTemplate(title = "404", template = "404-not-found")
    public String handleNotFoundException(Model model, Exception exception) {
        log.error(exception.getMessage());
        model.addAttribute("errorMessage", exception.getMessage());
        return layout;
    }

    @ExceptionHandler({UnauthorizedException.class})
    @TitleAndTemplate(title = "401", template = "401-unauthorized")
    public String handleUnauthorizedException(Model model, Exception exception) {
        log.error(exception.getMessage());
        model.addAttribute("errorMessage", exception.getMessage());
        return layout;
    }

    @ExceptionHandler({Exception.class})
    @TitleAndTemplate(title = "500", template = "500-internal-server-error")
    public String handleInternalServerException(Model model, Exception exception) {
        log.error(exception.getMessage());
        model.addAttribute("errorMessage", exception.getMessage());
        return layout;
    }
}
