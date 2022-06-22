package com.moazmahmud.health_data_collection.health_data.service;


import com.moazmahmud.health_data_collection.health_data.model.BloodPressure;
import com.moazmahmud.health_data_collection.health_data.model.BmiStatus;
import com.moazmahmud.health_data_collection.health_data.model.HypertensionStatus;
import com.moazmahmud.health_data_collection.utils.ValidationUtil;

import static com.moazmahmud.health_data_collection.health_data.model.BmiStatus.*;
import static com.moazmahmud.health_data_collection.health_data.model.HypertensionStatus.*;

public final class HealthUtil {
    private HealthUtil() {

    }

    public static Double calculateBmi(Double weightKg, Double heightInch) {
        if (ValidationUtil.isAnyNull(weightKg, heightInch)) {
            return null;
        }
        double heightMeter = heightInch * .0254;
        return weightKg / (heightMeter * heightMeter);
    }

    public static BmiStatus getBmiStatus(Double bmi) {
        if (bmi == null) {
            return null;
        }
        if (bmi < 18.5) {
            return UNDER_WEIGHT;
        }
        if (bmi < 23) {
            return BmiStatus.NORMAL;
        }
        if (bmi < 25) {
            return OVER_WEIGHT;
        }
        if (bmi < 30) {
            return PRE_OBESITY;
        }
        if (bmi < 35) {
            return OBESITY_CLASS_I;
        }
        if (bmi < 40) {
            return OBESITY_CLASS_II;
        }
        return OBESITY_CLASS_III;
    }

    public static HypertensionStatus getHypertensionStatus(BloodPressure bloodPressure) {
        var isAnyNull = bloodPressure == null
                        || ValidationUtil.isAnyNull(bloodPressure.getSystolicInMmHg(), bloodPressure.getDiastolicInMmHg());
        if (isAnyNull) {
            return null;
        }

        double high = bloodPressure.getSystolicInMmHg();
        double low = bloodPressure.getDiastolicInMmHg();
        if ((130 <= high && high <= 140) && (80 < low && low < 90)) {
            return HTN_1;
        }
        if (high > 140 && low > 120) {
            return HTN_CRISIS;
        }
        if (high > 140 && low > 90) {
            return HTN_2;
        }
        if (120 < high && high < 130 && low <= 80) {
            return ELEVATED;
        }
        return HypertensionStatus.NORMAL;
    }
}
