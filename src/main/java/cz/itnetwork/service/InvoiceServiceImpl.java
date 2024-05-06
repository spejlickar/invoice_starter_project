package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private PersonRepository personRepository;
    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity newInvoiceEntity = invoiceRepository.save(invoiceMapper.toEntity(invoiceDTO));
        newInvoiceEntity.setSeller(personRepository.getReferenceById( invoiceDTO.getSeller().getId()));
        newInvoiceEntity.setBuyer(personRepository.getReferenceById( invoiceDTO.getBuyer().getId()));
        return invoiceMapper.toDTO(newInvoiceEntity);
    }
}
