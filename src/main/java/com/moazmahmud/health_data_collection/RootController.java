package com.moazmahmud.health_data_collection;

import com.moazmahmud.health_data_collection.common.classes.BaseController;
import com.moazmahmud.health_data_collection.utils.LoggedInUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
