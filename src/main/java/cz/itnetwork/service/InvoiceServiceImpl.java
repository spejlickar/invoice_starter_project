package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        return invoiceMapper.toDTO(invoiceRepository.save(invoiceMapper.toEntity(invoiceDTO)));
    }
}
