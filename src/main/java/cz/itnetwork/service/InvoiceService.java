package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;


public interface InvoiceService {
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO getInvoiceById(long invoiceId);
    InvoiceDTO editInvoiceById(long invoiceId, InvoiceDTO invoiceDTO);
}
