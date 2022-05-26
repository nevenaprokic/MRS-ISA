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
import {getAttendanceReportYearlyCottage} from '../../services/ReservationService';
import { useState, useEffect } from "react";


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

const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July','August','September','October','November','December' ];
const backgroundColor= ['#E9D5CA', '#99C4C8','#F4BFBF','#FAF0D7','#827397', '#C2DED1','#C4DDFF','#F47C7C','#FFF2F2','#e3cab5']
const borderColor = ['#d9b7a5','#6aa9af','#ed9292','#f4dda4','#706284', '#acd2c1','#99c3ff','#f25a5a','#FAD4D4','#d6af8f']
//bez,plava,roze, zuta


export default function AttendanceReportYearly() {
    const [offerData, setOffereData] = React.useState();
    let data = {}
    useEffect(() => {
        async function setData() {
            const dataForReport = await getAttendanceReportYearlyCottage();
            setOffereData(dataForReport ? dataForReport.data : {});
            return dataForReport;
        }
        setData();
      }, []);
      if(offerData){
        let set = [];
        let i = 0;
        offerData.map((report) => {set.push( {
            label: report.offerName,
            data: report.value,
            borderColor: borderColor[i],
            backgroundColor: backgroundColor[i],
          },); i+=1;});
          console.log(set);
          console.log("UCITANO");
          data = {
                labels,
                datasets:set,
            };
            console.log(data);
        return  <Bar options={options} data={data} height={'900px'} width={'1000px'}/>;
      }
      
    
}