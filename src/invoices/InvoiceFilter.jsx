import React, {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {apiGet} from "../utils/api";

import InputSelect from "../components/InputSelect";

const InvoiceFilter = (props) => {
    
    const handleChange = (e) => {
        props.handleChange(e);
    };

    const handleSubmit = (e) => {
        props.handleSubmit(e);
    };

    const filter = props.filter;

    return (
        <div>
            <form onSubmit={handleSubmit} className="w-25">
                 <InputSelect
                        name="personID"
                        items={props.personList}
                        label="Vystavitel"
                        prompt="Nevybrán"
                        value={filter.sellerId}
                        handleChange={handleChange}
                    />
                <input type="submit" className="btn btn-secondary mt-2 mb-2" value={props.confirm}/>
            </form>
        </div>
    );
};
export default InvoiceFilter;
