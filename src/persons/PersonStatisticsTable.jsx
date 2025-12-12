import React from "react";

const PersonStatisticsTable = ({label, items}) => {
    return (
        <div>
            <p>
                {label} {items.length}
            </p>

            <table className="w-50 table table-bordered">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Jméno</th>
                    <th>Výdělek</th>
                </tr>
                </thead>
                <tbody>
                {items.map((item, index) => (
                    <tr key={index + 1}>
                        <td>{index + 1}</td>
                        <td>{item.personName}</td>
                        <td>{item.revenue}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default PersonStatisticsTable;