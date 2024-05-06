package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity newInvoiceEntity = invoiceRepository.save(invoiceMapper.toEntity(invoiceDTO));
        newInvoiceEntity.setSeller(personRepository.getReferenceById( invoiceDTO.getSeller().getId()));
        newInvoiceEntity.setBuyer(personRepository.getReferenceById( invoiceDTO.getBuyer().getId()));
        return invoiceMapper.toDTO(newInvoiceEntity);
    }

    @Override
    public InvoiceDTO getInvoiceById(long invoiceId) {
        InvoiceDTO foundInvoiceDTO = invoiceMapper.toDTO(fetchInvoiceById(invoiceId));
        foundInvoiceDTO.setSeller(personService.getPersonById(foundInvoiceDTO.getSeller().getId()));
        foundInvoiceDTO.setBuyer(personService.getPersonById(foundInvoiceDTO.getBuyer().getId()));
        return foundInvoiceDTO;
    }

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }
}
