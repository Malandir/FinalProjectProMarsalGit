package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.statistics.InvoiceStatistics;

import java.util.List;

public interface InvoiceService {

    /**
     * Create a new invoice
     *
     * @param invoiceDTO Invoice to create
     * @return newly created invoice
     * */
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    /**
     * Remove invoice by id
     *
     * @param id Invoice to delete
     * */
    void removeInvoice(long id);

    /**
     * Fetches all invoices, can use filter
     *
     * @param movieFilterEntity filter
     * @return List of all invoces
     * */
    List<InvoiceDTO> getAll(InvoiceFilter movieFilterEntity);

    /**
     * Get all invoice sold by selected person
     *
     * @param personIdn seller Identification number (not DB id)
     * @return List of all sold invoice by seller invoices
     * */
    List<InvoiceDTO> getSales(String personIdn);

    /**
     * Get all invoice bought by selected person
     *
     * @param personIdn buyer Identification number (not DB id)
     * @return List of all bought invoice by buyer invoices
     * */
    List<InvoiceDTO> getPurchases(String personIdn);

    /**
     * Fetch one invoice by id
     *
     * @param invoiceId Invoice to show
     * @return Selected invoice
     * */
    InvoiceDTO getInvoice(long invoiceId);

    /**
     * Edit invoice
     *
     * @param invoiceId Invoice to edit
     * @param invoiceDTO Edited invoice data
     * @return Saved edited invoice
     * */
    InvoiceDTO editInvoice(long invoiceId, InvoiceDTO invoiceDTO);

    /**
     * Get statistics of invoices
     * */
    InvoiceStatistics getStatistics();

}
