import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

import {apiGet} from "../utils/api";

const InvoiceDetail = () => {
    const {id} = useParams();
    const [invoice, setInvoice] = useState({
        buyer: {},
        seller: {}
    });

    useEffect(() => {
        apiGet("/api/invoices/show/"+id).then((data) => setInvoice(data)).catch((error) => console.error(error));
    }, [id]);

    const dueDateFormated = new Date(invoice.dueDate).toLocaleDateString("cs-CZ")

    const issuedFormated = new Date(invoice.issued).toLocaleDateString("cs-CZ")

    return (
        <>
            <div>
                <h1>Detail faktury</h1>
                <hr/>
                <h3>{invoice.invoiceNumber}</h3>
                <p>
                    <strong>Produkt:</strong>
                    <br/>
                    {invoice.product}
                </p>
                <p>
                    <strong>Cena:</strong>
                    <br/>
                    {invoice.price + invoice.vat} Kč
                    <br/>
                    <em>Cena bez DPH:</em>
                    <br/>
                    {invoice.price} Kč
                </p>
                <p>
                    <strong>Vystaveno:</strong>
                    <br/>
                    {issuedFormated}
                </p>
                <p>
                    <strong>Splatit do:</strong>
                    <br/>
                    {dueDateFormated}
                </p>
                <p>
                    <strong>Poznámka:</strong>
                    <br/>
                    {invoice.note}
                </p>
                <p>
                    <strong>Vystavil:</strong>
                    <br/>
                    {invoice.seller.name} ({invoice.seller.identificationNumber})
                </p>
                <p>
                    <strong>Koupil:</strong>
                    <br/>
                    {invoice.buyer.name} ({invoice.buyer.identificationNumber})
                </p>
            </div>
        </>
    );
};

export default InvoiceDetail;
