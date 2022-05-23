const dataTable = document.getElementById("data-table");
const dataTableBody = dataTable.querySelector("tbody");
const noDataFoundDiv = document.getElementById("no-data-found");
const downloadBtn = document.getElementById("download-btn");
let healthDataSearchResponseList = [];

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
            <td>
                <a class="btn btn-info" href="${healthDataSearchResponse.id}">Edit</a>
            </td>
        </tr>`;
}

async function showDataInTable() {
    try {
        healthDataSearchResponseList = await AjaxUtil.getPayload("/api/v1/health-data");
        const hasData = Array.isArray(healthDataSearchResponseList)
                        && healthDataSearchResponseList.length > 0;
        if (!hasData) {
            healthDataSearchResponseList = [];
            return;
        }
        dataTableBody.innerHTML = healthDataSearchResponseList.map(getTableRow).join("");
        hideElement(noDataFoundDiv);
        downloadBtn.disabled = false;
        showElement(dataTable);
    } catch (error) {
        healthDataSearchResponseList = [];
        console.error(error);
    }
}

document.addEventListener("DOMContentLoaded", async function () {
    hideElement(dataTable);
    downloadBtn.disabled = true;
    await showDataInTable();
});

function downloadCsvFile(csvString, fileName) {
    const blob = new Blob([csvString], {type: 'text/csv;charset=utf-8;'});
    const link = document.createElement("a");
    link.setAttribute("href", URL.createObjectURL(blob));
    link.setAttribute("download", `${fileName}.csv`);
    link.style.visibility = 'hidden';
    link.click();
}

downloadBtn.addEventListener("click", () => {
    if (!healthDataSearchResponseList) {
        console.error("healthDataSearchResponseList is Empty");
        return;
    }
    const fileName = "health_data";
    downloadObjectAsJson(
        healthDataSearchResponseList,
        fileName
    );
    downloadCsvFile(
        convertToCsvString(healthDataSearchResponseList),
        fileName
    );
});