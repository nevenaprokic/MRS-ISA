import React from 'react';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,} from 'chart.js';
import { Bar } from 'react-chartjs-2';
const { faker } = require('@faker-js/faker');

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

export const options = {
  indexAxis: 'y' ,
  elements: {
    bar: {
      borderWidth: 2,
    },
  },
  responsive: true,
  plugins: {
    legend: {
      position: 'right' ,
    },
    title: {
      display: true,
      text: ' Attendance report',
    },
  },
};

const labels = ['January', 'February'];
// 'January', 'February', 'March', 'April', 'May', 'June', 'July'
const backgroundColor= ['#E9D5CA', '#99C4C8','#F4BFBF','#FAF0D7','#827397', '#C2DED1','#C4DDFF','#F47C7C','#FFF2F2','#e3cab5']
const borderColor = ['#d9b7a5','#6aa9af','#ed9292','#f4dda4','#706284', '#acd2c1','#99c3ff','#f25a5a','#FAD4D4','#d6af8f']
//bez,plava,roze, zuta
export const data = {
  labels,
  datasets: [
    {
      label: 'Sumska vila',
      data: labels.map(() => faker.datatype.number({ min: 0, max: 10 })),
      borderColor: borderColor[0],
      backgroundColor: backgroundColor[0],
    },
    {
      label: 'Vikendica raj ',
      data: labels.map(() => faker.datatype.number({ min: 0, max: 10 })),
      borderColor: borderColor[1],
      backgroundColor: backgroundColor[1],
    },
    {
        label: 'Brvnara ',
        data: labels.map(() => faker.datatype.number({ min: 0, max: 10 })),
        borderColor: borderColor[2],
        backgroundColor: backgroundColor[2],
    },
    {
        label: 'Brvnara ',
        data: labels.map(() => faker.datatype.number({ min: 0, max: 10 })),
        borderColor: borderColor[3],
        backgroundColor: backgroundColor[3],
    },
    {
        label: 'Brvnara ',
        data: labels.map(() => faker.datatype.number({ min: 0, max: 10 })),
        borderColor: borderColor[4],
        backgroundColor: backgroundColor[4],
    },
    {
        label: 'Brvnara ',
        data: labels.map(() => faker.datatype.number({ min: 0, max: 10 })),
        borderColor: borderColor[5],
        backgroundColor: backgroundColor[5],
    },
    {
        label: 'Brvnara ',
        data: labels.map(() => faker.datatype.number({ min: 0, max: 10 })),
        borderColor: borderColor[6],
        backgroundColor: backgroundColor[6],
    },
    {
        label: 'Brvnara ',
        data: labels.map(() => faker.datatype.number({ min: 0, max: 10 })),
        borderColor: borderColor[7],
        backgroundColor: backgroundColor[7],
    },
    {
        label: 'Brvnara ',
        data: labels.map(() => faker.datatype.number({ min: 0, max: 10 })),
        borderColor: borderColor[8],
        backgroundColor: backgroundColor[8],
    },
  ],
};

export default function App() {
  return <Bar options={options} data={data} height={'500px'} width={'900px'}/>;
}