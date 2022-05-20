import fs from 'fs';

let rawdata = fs.readFileSync('rakin_city_mirpur_13_20_may_2022.json');
let dataList = JSON.parse(rawdata);

console.log(dataList.length);

function addHours(numOfHours, date = new Date()) {
    date.setTime(date.getTime() + numOfHours * 60 * 60 * 1000);
    return date;
}

dataList.forEach(data => {
    data.date = new Date(data.date).toString();
});

fs.writeFileSync('rakin_city_mirpur_13_20_may_2022_date_fixed.json', JSON.stringify(dataList));