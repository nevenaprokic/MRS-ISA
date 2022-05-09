import { useEffect, useState } from "react";
import { getAllRegistrationRegusestr } from "../../services/RegistrationRequestService";
import * as React from 'react';




function RegistrationRequestsList(){
    const [requests, setRequests] = useState();
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);

    useEffect(() => {
        async function setData(){
            const responseData = await getAllRegistrationRegusestr();
            console.log(responseData.data);
            setRequests(responseData.data ? responseData.data : {});
        }
        setData();

    }, []);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
      };
    
      const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
      };
    

    return (
        !!requests &&
        <div className="requestsContainer">

    </div>

    )
}

export default RegistrationRequestsList;