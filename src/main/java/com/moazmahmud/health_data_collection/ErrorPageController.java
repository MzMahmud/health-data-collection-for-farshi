package com.moazmahmud.health_data_collection;

import com.moazmahmud.health_data_collection.common.annotations.TitleAndTemplate;
import com.moazmahmud.health_data_collection.common.classes.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error-page")
@SuppressWarnings({"unused"})
public class ErrorPageController extends BaseController {

    @GetMapping("/400")
    @TitleAndTemplate(title = "Bad Request", template = "error-page/400-bad-request")
    public String get400ErrorPage(Model model) {
        return layout;
    }

    @GetMapping("/401")
    @TitleAndTemplate(title = "Unauthorized", template = "error-page/401-unauthorized")
    public String get401ErrorPage(Model model) {
        return layout;
    }

    @GetMapping("/404")
    @TitleAndTemplate(title = "Not Found", template = "error-page/404-not-found")
    public String get404ErrorPage(Model model) {
        return layout;
    }

    @GetMapping("/500")
    @TitleAndTemplate(title = "Internal Server Error", template = "error-page/500-internal-server-error")
    public String get500ErrorPage(Model model) {
        return layout;
    }
}
