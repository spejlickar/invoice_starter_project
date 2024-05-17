package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceFilter;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

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
        return invoiceMapper.toDTO( fetchInvoiceById(invoiceId));
    }

    @Override
    public InvoiceDTO editInvoiceById(long invoiceId, InvoiceDTO invoiceDTO) {
        InvoiceEntity editInvoiceEntity = fetchInvoiceById(invoiceId);
        return completeInvoice(invoiceRepository.save(invoiceMapper.updateEntity(invoiceDTO,editInvoiceEntity)));
    }

    @Override
    public void removeInvoice(long invoiceId) {
        invoiceRepository.delete(fetchInvoiceById(invoiceId));
    }

    @Override
    public List<InvoiceDTO> getAllInvoiceByFilter(InvoiceFilter invoiceFilter) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);
        return invoiceRepository.findAll(invoiceSpecification,PageRequest.of(0,invoiceFilter.getLimit()))
                .getContent()
                .stream()
                .map(i->invoiceMapper.toDTO(i))
                .toList();
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
