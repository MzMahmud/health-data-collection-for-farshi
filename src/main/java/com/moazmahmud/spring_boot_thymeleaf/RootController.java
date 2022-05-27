package com.moazmahmud.spring_boot_thymeleaf;

import com.moazmahmud.spring_boot_thymeleaf.common.classes.BaseController;
import com.moazmahmud.spring_boot_thymeleaf.utils.LoggedInUserUtil;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("")
public class RootController extends BaseController {
    @GetMapping
    public String getRootPage() {
        if (LoggedInUserUtil.hasAnyAuthority("HEALTH_DATA_ADD")) {
            return "redirect:/health-data";
        }
        boolean hasViewEditDeleteAuthority = LoggedInUserUtil.hasAnyAuthority(
                "HEALTH_DATA_VIEW",
                "HEALTH_DATA_EDIT",
                "HEALTH_DATA_DELETE"
        );
        if (hasViewEditDeleteAuthority) {
            return "redirect:/health-data/search";
        }
        return "redirect:/error-page/401";
    }
}
