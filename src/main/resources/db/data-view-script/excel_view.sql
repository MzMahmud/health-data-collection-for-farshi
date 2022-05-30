SELECT location_name                     AS "Location Name",
       event_date                        AS "Event Date",
       age                               AS "Age",
       gender                            AS "Gender",
       religion                          AS "Religion",
       height_in_inch                    AS "Height (Inch)",
       weight_in_kg                      AS "Weight (Kg)",
       bmi                               AS "BMI",
       bmi_status                        AS "BMI Status",
       blood_pressure_systolic_in_mm_hg  AS "Systolic Blood Pressure (MM Hg)",
       blood_pressure_diastolic_in_mm_hg AS "Diastolic Blood Pressure (MM Hg)",
       hypertension_status               AS "Hypertension Status",
       blood_sugar_in_milli_mole_per_l   AS "Blood Sugar (m mole/L)",
       physical_activity                 AS "Physical Activity",
       carb_intake_frequency             AS "Carb Intake Frequency",
       cereal_quality                    AS "cereal Quality"
FROM health_data
WHERE is_deleted = FALSE;
