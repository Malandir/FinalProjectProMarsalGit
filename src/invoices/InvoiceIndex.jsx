import React, {useEffect, useState} from "react";

import {apiDelete, apiGet} from "../utils/api";

import InvoiceTable from "./InvoiceTable";

import InvoiceFilter from "./InvoiceFilter";

const InvoiceIndex = () => {
    const [invoices, setInvoices] = useState([]);
    const [personListState, setPersonList] = useState([]);
    const [filterState, setFilter] = useState({
        sellerId: undefined,
    });
    
    const deleteInvoice = async (id) => {
            try {
                await apiDelete("/api/invoices/" + id);
            } catch (error) {
                console.log(error.message);
                alert(error.message)
            }
            setInvoices(invoices.filter((item) => item._id !== id));
        };

    useEffect(() => {
        apiGet("/api/invoices").then((data) => setInvoices(data));
        apiGet("/api/persons").then((data) => setPersonList(data));
    }, []);

    const handleChange = (e) => {
        const search = Number(e.target.value);
        setFilter(prev => ({...prev, sellerId: Number.isFinite(search) ? search : null}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const params = filterState;
        const data = await apiGet("/api/invoices", params);
        setInvoices(data);
    };

    return (
        <div>
            <h1>Seznam faktur</h1>
             <InvoiceFilter
                handleChange={handleChange}
                handleSubmit={handleSubmit}
                personList={personListState}
                filter={filterState}
                confirm="Vyhledat"
            />
            <InvoiceTable
                deleteInvoice={deleteInvoice}
                items={invoices}
                label="Počet faktur:"
            />
        </div>
    );
};
export default InvoiceIndex;