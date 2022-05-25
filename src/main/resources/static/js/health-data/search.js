const dataTable = document.getElementById("data-table");
const dataTableBody = dataTable.querySelector("tbody");
const noDataFoundDiv = document.getElementById("no-data-found");
const downloadBtn = document.getElementById("download-btn");
const eventDateInput = document.getElementById("eventDate");

function getTableRow(healthDataSearchResponse, index) {
    return `
        <tr>
            <td>${index + 1}</td>
            <td>${healthDataSearchResponse.eventDate}</td>
            <td>${healthDataSearchResponse.locationName}</td>
            <td>${healthDataSearchResponse.age}</td>
            <td>${healthDataSearchResponse.gender}</td>
            <td>${healthDataSearchResponse.heightInInch}</td>
            <td>${healthDataSearchResponse.weightInKg}</td>
        </tr>`;
}

async function deleteHealthData(id) {
    try {
        await AjaxUtil.sendDeleteRequest(`/api/v1/health-data/${id}`);
        location.reload();
    } catch (error) {
        console.error(error);
    }
}

async function showDataInTable(queryParamsObject = {}) {
    try {
        const healthDataSearchResponseList = await AjaxUtil.getPayload(`/api/v1/health-data`, queryParamsObject);
        const hasData = Array.isArray(healthDataSearchResponseList)
                        && healthDataSearchResponseList.length > 0;
        if (hasData) {
            dataTableBody.innerHTML = healthDataSearchResponseList.map(getTableRow).join("");
            downloadBtn.disabled = false;
            hideElement(noDataFoundDiv);
            showElement(dataTable);
        } else {
            dataTableBody.innerHTML = '';
            downloadBtn.disabled = true;
            hideElement(dataTable);
            showElement(noDataFoundDiv);
        }
    } catch (error) {
        console.error(error);
    }
}

document.addEventListener("DOMContentLoaded", async function () {
    hideElement(dataTable);
    downloadBtn.disabled = true;
    await showDataInTable();
});

function getSearchParamsObject() {
    const eventDate = eventDateInput.value;
    const queryParamsObject = {};
    if (eventDate) {
        queryParamsObject.eventDate = eventDate;
    }
    return queryParamsObject;
}

downloadBtn.addEventListener("click", async () => {
    try {
        const healthDataList = await AjaxUtil.getPayload("/api/v1/health-data/raw-data", getSearchParamsObject());
        const hasData = Array.isArray(healthDataList)
                        && healthDataList.length > 0;
        if (!hasData) {
            console.error("healthDataList has no data");
            alert("healthDataList has no data");
            return;
        }
        downloadObjectAsJson(healthDataList, "health_data");
    } catch (error) {
        console.error(error);
    }
});

eventDateInput.onchange = async () => {
    await showDataInTable(getSearchParamsObject());
};