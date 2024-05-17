package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {
    /**
     * vrati pocet vsech faktur
     * @return pocet vsech faktur
     */
    @Query(value = "SELECT COUNT(*) FROM invoice", nativeQuery = true)
    long getInvoicesCount();

    /**
     * vrati celkovou vyfakturovanou cenu vsech faktur
     * @return celkovou vyfakturovanou cenu vsech faktur
     */
    @Query(value = "SELECT SUM(price) FROM invoice", nativeQuery = true)
    long getAllTimeSum();

    /**
     * vrati celkovou vyfakturovanou cenu vsech faktur za letošek
     * @return celkovou vyfakturovanou cenu vsech faktur za letošek
     */
    @Query(value = "SELECT SUM(price) FROM invoice WHERE YEAR(due_date) = YEAR(CURDATE())", nativeQuery = true)
    long getCurrentYearSum();
}
