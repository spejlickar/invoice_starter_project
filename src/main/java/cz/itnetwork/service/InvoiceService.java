package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;


public interface InvoiceService {
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO getInvoiceById(long invoiceId);
    InvoiceDTO editInvoiceById(long invoiceId, InvoiceDTO invoiceDTO);
    void removePerson(long invoiceId);
    InvoiceStatisticsDTO getInvoicesStatistics();
}
