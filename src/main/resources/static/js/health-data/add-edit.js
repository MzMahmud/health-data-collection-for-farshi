const healthDataForm = document.getElementById('health-data-form');
const heightInput = document.getElementById('height');
const weightInput = document.getElementById('weight');
const bmiInput = document.getElementById("bmi");
const ageInput = document.getElementById("age");
const nutritionalStatusInput = document.getElementById("nutritionalStatus");
const bloodPressureLowInput = document.getElementById("bloodPressureLow");
const bloodPressureHighInput = document.getElementById("bloodPressureHigh");
const bloodPressureStatusInput = document.getElementById("bloodPressureStatus");
const eventDateInput = document.getElementById("eventDate");
const locationInput = document.getElementById("location");

const appLocalStoragePrefix = 'health-data-collection';

function setDefaultInputsToLocalStorage(data) {
    localStorage.setItem(`${appLocalStoragePrefix}-data`, JSON.stringify(data));
}

function setDefaultInputsFromLocalStorage() {
    const data = JSON.parse(localStorage.getItem(`${appLocalStoragePrefix}-data`)) ?? {};
    if(data.locationName == null) {
        locationInput.value = '';
        locationInput.focus();
    } else {
        locationInput.value = data.locationName;
    }
    eventDateInput.value = data.eventDate ?? new Date().toJSON().split('T')[0];
}

function resetForm() {
    healthDataForm.reset();
    ageInput.focus();
    setDefaultInputsFromLocalStorage();
}

document.addEventListener("DOMContentLoaded", function () {
    resetForm();
});

healthDataForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries());
    console.debug(data);
    setDefaultInputsToLocalStorage(data);
    await saveInDatabase(data);
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

async function saveInDatabase(data) {
    const url = "/api/v1/health-data";

    try {
        await AjaxUtil.postAsJson(url, data);
        successAlertDiv.classList.remove('d-none');
        setTimeout(() => {
            successAlertDiv.classList.add('d-none');
            resetForm();
        }, 1000);
    } catch (error) {
        console.error(error);
    }
}