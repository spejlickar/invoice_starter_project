package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;

public interface InvoiceService {
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO getInvoiceById(long invoiceId);
}
