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



const locationInput = document.getElementById("location");

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

