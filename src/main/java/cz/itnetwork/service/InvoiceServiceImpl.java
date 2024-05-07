package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
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
    private PersonService personService;

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        return completeInvoice(invoiceRepository.save(invoiceMapper.toEntity(invoiceDTO)));
    }

    @Override
    public InvoiceDTO getInvoiceById(long invoiceId) {
        return completeInvoice(fetchInvoiceById(invoiceId));
    }

    @Override
    public InvoiceDTO editInvoiceById(long invoiceId, InvoiceDTO invoiceDTO) {
        invoiceDTO.setId(invoiceId);
        return completeInvoice(invoiceRepository.save(invoiceMapper.toEntity(invoiceDTO)));
    }

    @Override
    public void removePerson(long invoiceId) {
        invoiceRepository.delete(fetchInvoiceById(invoiceId));
    }

//    /////////////private methods//////////////////////////////////////////////////////////////////////////

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }

    private InvoiceDTO completeInvoice(InvoiceEntity invoiceEntity){
        InvoiceDTO invoiceDTO = invoiceMapper.toDTO(invoiceEntity);
        invoiceDTO.setSeller(personService.getPersonById(invoiceDTO.getSeller().getId()));
        invoiceDTO.setBuyer(personService.getPersonById(invoiceDTO.getBuyer().getId()));
        return invoiceDTO;
    }

    @Override
    public InvoiceStatisticsDTO getInvoicesStatistics() {
        InvoiceStatisticsDTO resultStatistic = new InvoiceStatisticsDTO();
        resultStatistic.setInvoicesCount(invoiceRepository.getInvoicesCount());
        resultStatistic.setAllTimeSum(invoiceRepository.getAllTimeSum());
        resultStatistic.setCurrentYearSum(invoiceRepository.getCurrentYearSum());
        return resultStatistic;
    }
}
