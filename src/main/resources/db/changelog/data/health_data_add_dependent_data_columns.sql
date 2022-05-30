ALTER TABLE health_data
    ADD COLUMN bmi DOUBLE PRECISION,
    ADD COLUMN bmi_status          VARCHAR(255),
    ADD COLUMN hypertension_status VARCHAR(255);