package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    List<InvoiceEntity> findBySeller_IdentificationNumber(String personIdn);

    List<InvoiceEntity> findByBuyer_IdentificationNumber(String personIdn);

    @Query("""
       SELECT SUM(i.price)
       FROM invoice i
       WHERE YEAR(i.issued) = YEAR(CURRENT_DATE)
       """)
    BigDecimal getCurrentYearSum();

    @Query("""
           SELECT SUM(i.price)
           FROM invoice i
           """)
    BigDecimal getAllTimeSum();

    @Query("""
           SELECT SUM(i.price)
           FROM invoice i
           WHERE i.seller.id = :sellerId
           """)
    BigDecimal getRevenue(@Param("sellerId") Long sellerId);
}
