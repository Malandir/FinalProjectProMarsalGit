package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.statistics.InvoiceStatistics;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO){
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getInvoices(InvoiceFilter invoiceFilter){
        return invoiceService.getAll(invoiceFilter);
    }

    @GetMapping("/invoices/show/{invoiceId}")
    public InvoiceDTO getPerson(@PathVariable long invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }

    @DeleteMapping("/invoices/{invoiceId}")
    public void deleteInvoice(@PathVariable Long invoiceId) {
        invoiceService.removeInvoice(invoiceId);
    }

    @PutMapping("/invoices/edit/{invoiceId}")
    public InvoiceDTO editInvoice(@PathVariable long invoiceId, @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.editInvoice(invoiceId, invoiceDTO);
    }

    @GetMapping("/identification/{personIdn}/sales")
    public List<InvoiceDTO> getSales(@PathVariable String personIdn){
        return invoiceService.getSales(personIdn);
    }

    @GetMapping("/identification/{personIdn}/purchases")
    public List<InvoiceDTO> getPurchases(@PathVariable String personIdn){
        return invoiceService.getPurchases(personIdn);
    }

    @GetMapping("/invoices/statistics")
    public InvoiceStatistics getInvoiceStatistics() {
        return invoiceService.getStatistics();
    }
}
