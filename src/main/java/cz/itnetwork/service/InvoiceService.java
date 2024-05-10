package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceFilter;
import cz.itnetwork.dto.InvoiceStatisticsDTO;

import java.util.List;


public interface InvoiceService {
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO getInvoiceById(long invoiceId);
    InvoiceDTO editInvoiceById(long invoiceId, InvoiceDTO invoiceDTO);
    void removeInvoice(long invoiceId);
    InvoiceStatisticsDTO getInvoicesStatistics();
    List<InvoiceDTO> getAllInvoiceByFilter(InvoiceFilter invoiceFilter);
}
