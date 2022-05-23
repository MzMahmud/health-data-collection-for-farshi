const dataTable = document.getElementById("data-table");
const dataTableBody = dataTable.querySelector("tbody");
const noDataFoundDiv = document.getElementById("no-data-found");
const downloadBtn = document.getElementById("download-btn");

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
                <button type="button" 
                        onclick="deleteHealthData('${healthDataSearchResponse.id}')"
                        class="btn btn-outline-danger">
                    Delete
                </button>
            </td>
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

async function showDataInTable() {
    try {
        const healthDataSearchResponseList = await AjaxUtil.getPayload("/api/v1/health-data");
        const hasData = Array.isArray(healthDataSearchResponseList)
                        && healthDataSearchResponseList.length > 0;
        if (!hasData) {
            return;
        }
        dataTableBody.innerHTML = healthDataSearchResponseList.map(getTableRow).join("");
        hideElement(noDataFoundDiv);
        downloadBtn.disabled = false;
        showElement(dataTable);
    } catch (error) {
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

downloadBtn.addEventListener("click", async () => {
    try {
        const healthDataList = await AjaxUtil.getPayload("/api/v1/health-data/raw-data");
        const hasData = Array.isArray(healthDataList)
                        && healthDataList.length > 0;
        if (!hasData) {
            console.error("healthDataList has no data");
            return;
        }
        const fileName = "health_data";
        downloadObjectAsJson(
            healthDataList,
            fileName
        );
        downloadCsvFile(
            convertToCsvString(healthDataList),
            fileName
        );
    } catch (error) {
        console.error(error);
    }
});