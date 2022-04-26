import "./CottageProfilePage.scss";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import EuroIcon from "@mui/icons-material/Euro";
import {getAdditionalServiceByOffer} from '../../../services/AdditionalServicesService';
import { useState, useEffect } from "react";

function PriceList({ offer }) {
  function createData() {
    serviceData.forEach((data) => {
        let name = data.serviceName;
        let price = data.servicePrice;
        rows.push({name, price});
      });
  }
  let rows = [];
  const [serviceData, setServiceData] = useState();
  useEffect(() => {
    async function setData() {
      const serviceData = await getAdditionalServiceByOffer(offer.id);
      setServiceData(serviceData.data ? serviceData.data : {});
      return serviceData.data;
    }
    setData();
  }, []);
  if (serviceData) {
    createData();
    return (
      <div className="pricesContainer">
        <div>
          <div className="boxItem">
            <EuroIcon
              color="action"
              sx={{ fontSize: "30px", marginTop: "2px" }}
            />
          </div>
          <label className="priceListTittle"> Pricelist</label>
        </div>
        <div className="basePrice">
          <label>Basic price: {offer.price} €</label>
        </div>

        <div className="tableContainer">
          <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
              <TableHead>
                <TableRow>
                  <TableCell sx={{ fontWeight: "bold", color: "#99A799" }}>
                    Additional service
                  </TableCell>
                  <TableCell
                    sx={{ fontWeight: "bold", color: "#99A799" }}
                    align="left"
                  >
                    Price €
                  </TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                  <TableRow
                    key={row.name}
                    sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                  >
                    <TableCell component="th" scope="row">
                      {row.name}
                    </TableCell>
                    <TableCell align="left">{row.price}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </div>
      </div>
    );
  }
}

export default PriceList;
