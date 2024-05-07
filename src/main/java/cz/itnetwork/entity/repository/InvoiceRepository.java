package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
//    @Query(value = "SELECT * FROM invoice WHERE buyer_id IN ( SELECT id FROM person WHERE identification_number = :identificationNumber AND hidden = 0)",
//    nativeQuery = true)
//    List<InvoiceEntity> getInvoiceByIdentificationNumberBuyer(@Param("identificationNumber") String identificationNumber);
    @Query(value = "SELECT COUNT(*) FROM invoice", nativeQuery = true)
    long getInvoicesCount();

    @Query(value = "SELECT SUM(price) FROM invoice", nativeQuery = true)
    long getAllTimeSum();

    @Query(value = "SELECT SUM(price) FROM invoice WHERE YEAR(due_date) = YEAR(CURDATE())", nativeQuery = true)
    long getCurrentYearSum();
}
