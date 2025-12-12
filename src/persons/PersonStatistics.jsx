import React, {useEffect, useState} from "react";
import {apiGet} from "../utils/api";
import PersonStatisticsTable from "./PersonStatisticsTable";

const PersonStatistics = ({}) => {
    const [statistics, setStatistics] = useState([]);
            
    useEffect(() => {apiGet("/api/persons/statistics").then((data) => setStatistics(data));}, []);

    return (
        <div>
            <h1>Statistiky osob</h1>
            <PersonStatisticsTable
                items={statistics}
                label="Počet osob:"
            />
        </div>
    );
};

export default PersonStatistics;