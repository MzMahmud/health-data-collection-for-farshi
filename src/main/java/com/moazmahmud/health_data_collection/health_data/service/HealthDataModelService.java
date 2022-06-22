package com.moazmahmud.health_data_collection.health_data.service;

import com.moazmahmud.health_data_collection.common.enums.Gender;
import com.moazmahmud.health_data_collection.common.enums.Religion;
import com.moazmahmud.health_data_collection.health_data.model.CarbIntakeFrequency;
import com.moazmahmud.health_data_collection.health_data.model.CerealQuality;
import com.moazmahmud.health_data_collection.health_data.model.PhysicalActivity;
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
