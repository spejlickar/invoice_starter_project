package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    @Query(value = "SELECT COUNT(*) FROM invoice", nativeQuery = true)
    long getInvoicesCount();

    @Query(value = "SELECT SUM(price) FROM invoice", nativeQuery = true)
    long getAllTimeSum();

    @Query(value = "SELECT SUM(price) FROM invoice WHERE YEAR(due_date) = YEAR(CURDATE())", nativeQuery = true)
    long getCurrentYearSum();
}
