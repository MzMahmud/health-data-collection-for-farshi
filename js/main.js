const healthDataForm = document.getElementById('health-data-form');

healthDataForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const formElement = event.target;
    const formData = new FormData(formElement);

    const data = Object.fromEntries(formData.entries());

    console.debug(data);
    saveInLocalStorage(data);
});



const heightInput = document.getElementById('height');
const weightInput = document.getElementById('weight');
const bmiInput = document.getElementById("bmi");
const nutritionalStatusInput = document.getElementById("nutritionalStatus");
const bloodPressureLowInput = document.getElementById("bloodPressureLow");
const bloodPressureHighInput = document.getElementById("bloodPressureHigh");
const bloodPressureStatusInput = document.getElementById("bloodPressureStatus");



function setBmi() {
    const bmi = calculateBmi(Number(weightInput.value), Number(heightInput.value));
    bmiInput.value = bmi;
    nutritionalStatusInput.value = getBmiStatus(bmi);
}

function setBloodPressureStatus() {
    bloodPressureStatusInput.value = getBloodPressureStatus(
        Number(bloodPressureLowInput.value),
        Number(bloodPressureHighInput.value)
    )
}

heightInput.addEventListener('input', () => setBmi());
weightInput.addEventListener('input', () => setBmi());

bloodPressureHighInput.addEventListener('input', () => setBloodPressureStatus());
bloodPressureLowInput.addEventListener('input', () => setBloodPressureStatus());


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

const healthDataStoreName = "healthDataStoreName";
const successAlertDiv = document.getElementById('successAlert');

function saveInLocalStorage(data) {
    const dataList = getDataList();
    data.date = new Date();
    dataList.push(data);
    localStorage.setItem(healthDataStoreName, JSON.stringify(dataList));
    successAlertDiv.classList.remove('d-none');

    setTimeout(() => {
        successAlertDiv.classList.add('d-none');
        healthDataForm.reset();
    }, 1000);
}

function getDataList() {
    return JSON.parse(localStorage.getItem(healthDataStoreName) ?? "[]");
}


function downloadObjectAsJson(exportObj, exportName) {
    const dataStr = "data:text/json;charset=utf-8," + encodeURIComponent(JSON.stringify(exportObj));
    const downloadAnchorNode = document.createElement('a');
    downloadAnchorNode.setAttribute("href", dataStr);
    downloadAnchorNode.setAttribute("download", `${exportName}_data.json`);
    downloadAnchorNode.click();
}

const locationInput = document.getElementById("location");

function convertToCsvString(objectArray) {
    const headers = getHeaders(objectArray);
    return [
        headers.join(","),
        ...objectArray.map(object => getCsvRowFromObject(object, headers))
    ].join("\r\n");
}


function getCsvRowFromObject(object, headers) {
    return headers.map(header => getCsvCellValue(object[header])).join(",");
}

function getCsvCellValue(string) {
    if (string == null) {
        return "";
    }
    return String(string).includes(",") ? `"${string}"` : `${string}`;
}

function getHeaders(objectArray) {
    const header = new Set();
    for (const object of objectArray) {
        for (const field in object) {
            header.add(field);
        }
    }
    return [...header];
}

function downloadCsvFile(csvString, fileName) {
    const blob = new Blob([csvString], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement("a");
    link.setAttribute("href", URL.createObjectURL(blob));
    link.setAttribute("download", `${fileName}.csv`);
    link.style.visibility = 'hidden';
    link.click();
}

document.getElementById("download-btn").addEventListener("click", () => {
    const dataList = getDataList();
    downloadObjectAsJson(
        dataList,
        locationInput.value
    );
    downloadCsvFile(
        convertToCsvString(dataList),
        locationInput.value
    );
});

