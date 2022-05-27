import fs from 'fs';

let rawdata = fs.readFileSync('rakin_city_mirpur_13_20_may_2022.json');
let dataList = JSON.parse(rawdata);

console.log(dataList.length);


function genderConverter(genderStr) {
    switch (genderStr) {
        case 'male': return `'MALE'`;
        case 'female': return `'FEMALE'`;
        case 'other': return `'OTHER'`;
    }
    return `'PREFER_NOT_TO_TELL'`;
}

function religionConverter(religionStr) {
    switch (religionStr) {
        case "islam": return `'ISLAM'`;
        case "hinduism": return `'HINDUISM'`;
        case "budhism": return `'BUDDHISM'`;
        case "cristianity": return `'CHRISTIANITY'`;
    }
    return `'PREFER_NOT_TO_TELL'`;
}

function carbIntakeFrequencyConverter(carbIntakeFrequency) {
    switch (carbIntakeFrequency) {
        case "oneTwo": return `'ONE_TWO_TIMES'`;
        case "twoThree": return `'TWO_THREE_TIMES'`;
        case "threeFour": return `'THREE_FOUR_TIMES'`;
        case "moreThanFour": return `'MORE_THAN_FOUR_TIMES'`;
    }
    return `null`;
}

function cerealQualityConverter(cerealQuality) {
    switch (cerealQuality) {
        case "simple": return `'SIMPLE'`;
        case "complex": return `'COMPLEX'`;
        case "both": return `'BOTH'`;
    }
    return `null`;
}

function physicalActivityConverter(physicalActivity) {
    switch (physicalActivity) {
        case "bedRest": return `'BED_REST'`;
        case "sedentary": return `'SEDENTARY'`;
        case "lightActivity": return `'LIGHT_ACTIVITY'`;
        case "moderate": return `'MODERATE'`;
        case "high": return `'HIGH'`;
    }
    return `null`;
}

function getSqlInsertQuery(data, index) {
    const dataInsertionTime = new Date(data.dataInsertionTime);
    const insertTime = dataInsertionTime.toLocaleString();
    const eventDate = dataInsertionTime.toISOString().split('T')[0];
    const locationName = 'Rakin City, Mirpur - 13';
    return `
INSERT INTO health_data (id, created_at, is_deleted, modified_at, age, blood_pressure_diastolic_in_mm_hg, blood_pressure_systolic_in_mm_hg, blood_sugar_in_milli_mole_per_l, carb_intake_frequency, cereal_quality, event_date, gender, height_in_inch, location_name, physical_activity, religion, weight_in_kg) 
VALUES (${index + 1}, '${insertTime}', false, '${insertTime}', ${data.age}, ${data.bloodPressureLow}, ${data.bloodPressureHigh}, ${data.bloodSugar}, ${carbIntakeFrequencyConverter(data.carbIntakeFrequency)}, ${cerealQualityConverter(data.cerealQuality)}, '${eventDate}', ${genderConverter(data.gender)}, ${data.height}, '${locationName}', ${physicalActivityConverter(data.physicalActivity)}, ${religionConverter(data.religion)}, ${data.weight});
        `.trim();
}

const sqlString =
    dataList
        .map(getSqlInsertQuery)
        .join('\n')
        .trim();

fs.writeFileSync('rakin_city_mirpur_data.sql', sqlString);
