package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.InvoiceFilter;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.InvoiceEntity_;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.PersonEntity_;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class InvoiceSpecification implements Specification<InvoiceEntity> {
    private final InvoiceFilter invoiceFilter;

    public InvoiceSpecification(InvoiceFilter invoiceFilter) {
        this.invoiceFilter = invoiceFilter;
    }

    /**
     * statistika
     * @param root root
     * @param query query
     * @param criteriaBuilder criteriaBuilder
     * @return predicate
     */
    @Override
    public Predicate toPredicate(Root<InvoiceEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (invoiceFilter.getBuyerID() != null) {
            Join<PersonEntity,InvoiceEntity> buyerJoin = root.join(InvoiceEntity_.BUYER);
            predicates.add(criteriaBuilder.equal(buyerJoin.get(PersonEntity_.IDENTIFICATION_NUMBER),invoiceFilter.getBuyerID()));
        }
        if (invoiceFilter.getSellerID() != null) {
            Join<PersonEntity,InvoiceEntity> buyerJoin = root.join(InvoiceEntity_.SELLER);
            predicates.add(criteriaBuilder.equal(buyerJoin.get(PersonEntity_.IDENTIFICATION_NUMBER),invoiceFilter.getSellerID()));
        }
        if(invoiceFilter.getProduct() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.trim(root.get(InvoiceEntity_.PRODUCT)), "%"+invoiceFilter.getProduct().toLowerCase().trim()+"%"));
        }
        if(invoiceFilter.getMinPrice() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(InvoiceEntity_.PRICE), invoiceFilter.getMinPrice()));
        }
        if(invoiceFilter.getMaxPrice() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(InvoiceEntity_.PRICE), invoiceFilter.getMaxPrice()));
        }
        return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
    }
}
