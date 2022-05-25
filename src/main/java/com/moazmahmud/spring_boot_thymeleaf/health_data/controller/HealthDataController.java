package com.moazmahmud.spring_boot_thymeleaf.health_data.controller;

import com.moazmahmud.spring_boot_thymeleaf.annotations.TitleAndTemplate;
import com.moazmahmud.spring_boot_thymeleaf.common.classes.BaseController;
import com.moazmahmud.spring_boot_thymeleaf.health_data.model.HealthDataAddRequest;
import com.moazmahmud.spring_boot_thymeleaf.health_data.service.HealthDataModelService;
import com.moazmahmud.spring_boot_thymeleaf.health_data.service.HealthDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/health-data")
@RequiredArgsConstructor
public class HealthDataController extends BaseController {

    private final HealthDataModelService healthDataModelService;
    private final HealthDataService healthDataService;

    @GetMapping
    @TitleAndTemplate(title = "Health Data | Data Entry", template = "health-data/add-edit")
    public String getAddPage(Model model) {
        healthDataModelService.setAddEditPageStaticData(model);
        model.addAttribute("healthDataAddRequest", HealthDataAddRequest.getEmptyInstance());
        return layout;
    }

    @GetMapping("/{id}")
    @TitleAndTemplate(title = "Health Data | Data Entry", template = "health-data/add-edit")
    public String getEditPage(
            Model model,
            @PathVariable("id") Long id
    ) {
        healthDataModelService.setAddEditPageStaticData(model);
        model.addAttribute("healthDataAddRequest", healthDataService.getAddRequestById(id));
        return layout;
    }

    @GetMapping("/search")
    @TitleAndTemplate(title = "Health Data | Existing Data", template = "health-data/search")
    public String getSearchPage(Model model) {
        return layout;
    }
}
