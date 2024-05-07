package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDTO getInvoice(@PathVariable Long invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }

    @PutMapping("/invoices/{invoiceId}")
    public InvoiceDTO editInvoice(@RequestBody InvoiceDTO invoiceDTO,@PathVariable Long invoiceId){
        return invoiceService.editInvoiceById(invoiceId,invoiceDTO);
    }

    @DeleteMapping("/invoices/{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long invoiceId) {
        invoiceService.removePerson(invoiceId);
    }

    @GetMapping("/invoices/statistics")
    public InvoiceStatisticsDTO getInvoicesStatistics() {
        return invoiceService.getInvoicesStatistics();
    }

}
