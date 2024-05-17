package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceFilter;
import cz.itnetwork.dto.InvoiceStatisticsDTO;

import java.util.List;


public interface InvoiceService {
    /**
     * přidá fakturu do database dle dat (invoiceDTO)
     * @param invoiceDTO data nove faktury bez id
     * @return data nove fatury s id i s prodavajicim i fakturovaným
     */
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    /**
     * vratí fakturu dle id faktury (invoiceId), pokud neexistuje tak se vyvola výjimka
     * @param invoiceId id faktury
     * @return data hledane fatury i s prodavajicim i fakturovaným
     */
    InvoiceDTO getInvoiceById(long invoiceId);

    /**
     * upravi existujici faturu dle id faktury (invoiceId) a dat (invoiceDTO),
     * pokud faktura neexistuje tak se vyvola vyjimka
     * @param invoiceId id faktury
     * @param invoiceDTO upravena data
     * @return data upravene fatury s id i s prodavajicim i fakturovaným
     */
    InvoiceDTO editInvoiceById(long invoiceId, InvoiceDTO invoiceDTO);

    /**
     * vymaze fakturu dle id faktury (invoiceId), pokud neexistuje vyvola se vyjimka
     * @param invoiceId id faktury
     */
    void removeInvoice(long invoiceId);

    /**
     * vrati statistiky faktur
     * @return data statistiky(pocet vsech fatur, celkova vyfakturovana cena za letošek i za celou dobu)
     */
    InvoiceStatisticsDTO getInvoicesStatistics();

    /**
     * vypis faktur dle filtrace (invoiceFilter)
     * @param invoiceFilter filter fatur
     * @return vyfiltrovane faktury
     */
    List<InvoiceDTO> getAllInvoiceByFilter(InvoiceFilter invoiceFilter);
}
