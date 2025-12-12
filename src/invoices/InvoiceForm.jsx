import React, {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";

import {apiGet, apiPost, apiPut} from "../utils/api";

import InputField from "../components/InputField";
import InputSelect from "../components/InputSelect";
import FlashMessage from "../components/FlashMessage";

const InvoiceForm = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const [invoice, setInvoice] = useState({
        invoiceNumber: "",
        product: "",
        price: "",
        vat: "",
        issued: "",
        dueDate: "",
        note: "",
        buyer: {},
        seller: {}
    });

    const [personListState, setPersonList] = useState([]);
    const [sellerState, setSeller] = useState("");
    const [buyerState, setBuyer] = useState("");

    const [sentState, setSent] = useState(false);
    const [successState, setSuccess] = useState(false);
    const [errorState, setError] = useState(null);

    useEffect(() => {
        apiGet("/api/persons").then((data) => setPersonList(data));
    }, []);

    useEffect(() => {
        if (id) {
            apiGet("/api/invoices/edit/"+id).then((data) => setInvoice(data)).catch((error) => console.error(error));
        }
    }, [id]);

    const handleSubmit = (e) => {
        e.preventDefault();

        (id ? apiPut("/api/invoices/edit/" + id, invoice) : apiPost("/api/invoices", invoice))
            .then((data) => {
                setSent(true);
                setSuccess(true);
                navigate("/invoices");
            })
            .catch((error) => {
                console.log(error.message);
                setError(error.message);
                setSent(true);
                setSuccess(false);
            });
    };

    const sent = sentState;
    const success = successState;

    return (
        <div>
            <h1>{id ? "Upravit" : "Vytvořit"} fakturu</h1>
            <hr/>
            {errorState ? (
                <div className="alert alert-danger">{errorState}</div>
            ) : null}
            {sent && (
                <FlashMessage
                    theme={success ? "success" : ""}
                    text={success ? "Uložení faktury proběhlo úspěšně." : ""}
                />
            )}

            <form onSubmit={handleSubmit}>
                <InputField
                    required={true}
                    type="number"
                    name="invoiceNumber"
                    min="3"
                    label="Číslo faktury"
                    prompt="Zadejte číslo faktury"
                    value={invoice.invoiceNumber}
                    handleChange={(e) => {
                        setInvoice({...invoice, invoiceNumber: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="text"
                    name="product"
                    min="3"
                    label="Produkt"
                    prompt="Zadejte produkt"
                    value={invoice.product}
                    handleChange={(e) => {
                        setInvoice({...invoice, product: e.target.value});
                    }}
                />
                
                <InputField
                    required={true}
                    type="number"
                    name="cena"
                    min="3"
                    label="Cena"
                    prompt="Zadejte cenu produktu"
                    value={invoice.price}
                    handleChange={(e) => {
                        setInvoice({...invoice, price: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="number"
                    name="vat"
                    min="3"
                    label="DPH"
                    prompt="Zadejte DPH produktu"
                    value={invoice.vat}
                    handleChange={(e) => {
                        setInvoice({...invoice, vat: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="date"
                    name="issued"
                    min="3"
                    label="Vystaveno"
                    prompt="Kdy byla faktura vystavena"
                    value={invoice.issued}
                    handleChange={(e) => {
                        setInvoice({...invoice, issued: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="date"
                    name="dueDate"
                    min="3"
                    label="Splatit do"
                    prompt="Do kdy má být faktura splacena"
                    value={invoice.dueDate}
                    handleChange={(e) => {
                        setInvoice({...invoice, dueDate: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="text"
                    name="note"
                    label="Poznámka"
                    prompt="Zadejte poznámku"
                    value={invoice.note}
                    handleChange={(e) => {
                        setInvoice({...invoice, note: e.target.value});
                    }}
                />

                <InputSelect
                    name="seller"
                    items={personListState}
                    label="Vystavitel"
                    prompt="Vystavitelé"
                    value={sellerState}
                    handleChange={(e) => {
                        const selectedId = e.target.value;
                        setSeller(selectedId);
                        setInvoice({...invoice, seller: { _id: Number(selectedId) }});
                    }}
                />

                <InputSelect
                    name="buyer"
                    items={personListState}
                    label="Zákazník"
                    prompt="Zákazníci"
                    value={buyerState}
                    handleChange={(e) => {
                        const selectedId = e.target.value;
                        setBuyer(selectedId);
                        setInvoice({...invoice, buyer: { _id: Number(selectedId) }});
                    }}
                />

                <br/>
                <input type="submit" className="btn btn-primary" value="Uložit"/>
            </form>
        </div>
    );
};

export default InvoiceForm;