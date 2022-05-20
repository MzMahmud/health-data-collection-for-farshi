const healthDataForm = document.getElementById('health-data-form');
const heightInput = document.getElementById('height');
const weightInput = document.getElementById('weight');
const bmiInput = document.getElementById("bmi");
const ageInput = document.getElementById("age");
const nutritionalStatusInput = document.getElementById("nutritionalStatus");
const bloodPressureLowInput = document.getElementById("bloodPressureLow");
const bloodPressureHighInput = document.getElementById("bloodPressureHigh");
const bloodPressureStatusInput = document.getElementById("bloodPressureStatus");


document.addEventListener("DOMContentLoaded", function () {
    ageInput.focus();
});

healthDataForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const formElement = event.target;
    const formData = new FormData(formElement);

    const data = Object.fromEntries(formData.entries());

    console.debug(data);
    saveInLocalStorage(data);
});


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
        ageInput.focus();
    }, 1000);
}