package com.moazmahmud.spring_boot_thymeleaf.health_data.service;

import com.moazmahmud.spring_boot_thymeleaf.common.enums.Gender;
import com.moazmahmud.spring_boot_thymeleaf.common.enums.Religion;
import com.moazmahmud.spring_boot_thymeleaf.health_data.model.CarbIntakeFrequency;
import com.moazmahmud.spring_boot_thymeleaf.health_data.model.CerealQuality;
import com.moazmahmud.spring_boot_thymeleaf.health_data.model.PhysicalActivity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class HealthDataModelService {

    public void setAddEditPageStaticData(Model model) {
        model.addAttribute("genderList", Gender.genderList);
        model.addAttribute("religionList", Religion.religionList);
        model.addAttribute("physicalActivityList", PhysicalActivity.physicalActivityList);
        model.addAttribute("carbIntakeFrequencyList", CarbIntakeFrequency.carbIntakeFrequencyList);
        model.addAttribute("cerealQualityList", CerealQuality.cerealQualityList);
    }
}
