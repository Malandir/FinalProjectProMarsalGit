import React, {useEffect, useState} from "react";

import {apiDelete, apiGet} from "../utils/api";

const InvoiceStatistics = () => {
    const [statistics, setStatistics] = useState([]);
        
    useEffect(() => {apiGet("/api/invoices/statistics").then((data) => setStatistics(data));}, []);

    return (
        <div>
            <h1>Statistiky faktur</h1>
            <table className="w-50 table table-bordered">
                <tbody>
                    <tr>
                        <td className="w-50">Výdělek za tento rok</td>
                        <td className="w-50">{statistics.currentYearSum} Kč</td>
                    </tr>
                    <tr>
                        <td>Celkový výdělek</td>
                        <td>{statistics.allTimeSum} Kč</td>
                    </tr>
                    <tr>
                        <td>Prodáno faktur</td>
                        <td>{statistics.invoicesCount}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
};
export default InvoiceStatistics;