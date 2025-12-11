package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.entity.repository.specification.InvoiceSpecification;
import cz.itnetwork.entity.statistics.InvoiceStatistics;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO){
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);

        try {

            PersonEntity buyer = personRepository.getReferenceById(invoiceDTO.getBuyer().getId());
            PersonEntity seller = personRepository.getReferenceById(invoiceDTO.getSeller().getId());

            entity.setBuyer(buyer);
            entity.setSeller(seller);
        } catch (NoSuchElementException e) {

            throw new EntityNotFoundException("Buyer or seller not found");
        }

        entity = invoiceRepository.save(entity);

        return invoiceMapper.toDTO(entity);
    }

    @Override
    public void removeInvoice(long invoiceId) {
        try {
            InvoiceEntity invoice = fetchInvoiceById(invoiceId);
            invoiceRepository.delete(invoice);
        } catch (NotFoundException ignored) {
            // The contract in the interface states, that no exception is thrown, if the entity is not found.
        }

    }

    @Override
    public List<InvoiceDTO> getAll(InvoiceFilter invoiceFilter) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);

        return invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()))
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getSales(String personIdn) {

        List<InvoiceEntity> invoices = invoiceRepository.findBySeller_IdentificationNumber(personIdn);

        return invoices.stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getPurchases(String personIdn) {
        List<InvoiceEntity> invoices = invoiceRepository.findByBuyer_IdentificationNumber(personIdn);

        return invoices.stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getInvoice(long invoiceId) {
        InvoiceEntity invoiceEntity = fetchInvoiceById(invoiceId);
        return invoiceMapper.toDTO(invoiceEntity);
    }

    @Override
    public InvoiceDTO editInvoice(long invoiceId, InvoiceDTO invoiceDTO) {

        invoiceDTO.setId(invoiceId);
        InvoiceEntity entity = invoiceRepository.getReferenceById(invoiceId);
        invoiceMapper.updateInvoiceEntity(invoiceDTO, entity);

        try {
            PersonEntity buyer = personRepository.getReferenceById(invoiceDTO.getBuyer().getId());
            PersonEntity seller = personRepository.getReferenceById(invoiceDTO.getSeller().getId());

            entity.setBuyer(buyer);
            entity.setSeller(seller);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Buyer or seller not found");
        }

        entity = invoiceRepository.save(entity);

        return invoiceMapper.toDTO(entity);
    }

    @Override
    public InvoiceStatistics getStatistics() {

        BigDecimal currentYearSum = invoiceRepository.getCurrentYearSum();
        BigDecimal allTimeSum = invoiceRepository.getAllTimeSum();
        Long invoicesCount = invoiceRepository.count();

        return new InvoiceStatistics(currentYearSum, allTimeSum, invoicesCount);
    }

    // region: Private methods
    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }
    // endregion
}
