const dataTable = document.getElementById("data-table");
const dataTableHead = dataTable.querySelector("thead");
const dataTableBody = dataTable.querySelector("tbody");
const noDataFoundDiv = document.getElementById("no-data-found");
const downloadBtn = document.getElementById("download-btn");


function getTableRowFromObject(data, index, headers) {
    return `
        <tr>
            <td>${index + 1}</td>
            ${headers.map(header => `<td>${data[header]}</td>`).join("")}
        </tr>`;
}

function showDataInTable() {
    const dataList = getDataList();
    if (dataList.length === 0) {
        return;
    }
    const headers = getHeaders(dataList);
    dataTableHead.innerHTML = `
        <tr>
            <th>Serial No</th>
            ${headers.map(header => `<th scope="col">${header}</th>`).join("")}
        </tr>`;
    dataTableBody.innerHTML = dataList.map((data, index) => getTableRowFromObject(data, index, headers)).join("");
    hideElement(noDataFoundDiv);
    downloadBtn.disabled = false;
    showElement(dataTable);
}

document.addEventListener("DOMContentLoaded", function () {
    hideElement(dataTable);
    downloadBtn.disabled = true;
    showDataInTable();
});

const locationInput = document.getElementById("location");

function downloadCsvFile(csvString, fileName) {
    const blob = new Blob([csvString], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement("a");
    link.setAttribute("href", URL.createObjectURL(blob));
    link.setAttribute("download", `${fileName}.csv`);
    link.style.visibility = 'hidden';
    link.click();
}


downloadBtn.addEventListener("click", () => {
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