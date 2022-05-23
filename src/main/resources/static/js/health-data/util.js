function hideElement(element) {
    element.classList.add("d-none");
}

function showElement(element) {
    element.classList.remove("d-none");
}

function calculateBmi(weightKg, heightInch) {
    const heightMeter = heightInch * .0254;
    return weightKg / (heightMeter * heightMeter);
}

function getBmiStatus(bmi) {
    if (bmi < 18.5) {
        return "Under Weight";
    }
    if (bmi < 23) {
        return "Normal";
    }
    if (bmi < 25) {
        return "Over Weight";
    }
    if (bmi < 30) {
        return "Pre Obecity";
    }
    if (bmi < 35) {
        return "Obecity Class I";
    }
    if (bmi < 40) {
        return "Obecity Class II";
    }
    return "Obecity Class III";
}

function getBloodPressureStatus(low, high) {
    if ((130 <= high && high <= 140) && (80 < low && low < 90)) {
        return "HTN 1";
    }
    if (high > 140 && low > 90) {
        return "HTN 2";
    }
    if (120 < high && high < 130 && low <= 80) {
        return "Elevated";
    }
    if (high > 140 && low > 120) {
        return "HTN Crisis";
    }
    return "Normal";
}

function downloadObjectAsJson(exportObj, exportName) {
    const dataStr = "data:text/json;charset=utf-8," + encodeURIComponent(JSON.stringify(exportObj));
    const downloadAnchorNode = document.createElement('a');
    downloadAnchorNode.setAttribute("href", dataStr);
    downloadAnchorNode.setAttribute("download", `${exportName}.json`);
    downloadAnchorNode.click();
}