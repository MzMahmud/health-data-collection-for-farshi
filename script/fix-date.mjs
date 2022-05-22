import fs from 'fs';

let rawdata = fs.readFileSync('rakin_city_mirpur_13_20_may_2022.json');
let dataList = JSON.parse(rawdata);

console.log(dataList.length);

dataList.forEach(data => {
    data.date = new Date(data.date).toString();
    data.eventDate = '2022-05-20';
    data.dataInsertionTime = data.date;
    delete data.date;
});

fs.writeFileSync('rakin_city_mirpur_13_20_may_2022_fixed.json', JSON.stringify(dataList));