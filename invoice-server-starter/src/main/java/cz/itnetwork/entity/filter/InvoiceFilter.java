package cz.itnetwork.entity.filter;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceFilter {

    private Long buyerId = -1L;
    private Long sellerId = -1L;
    private String product = "";
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer limit = 10;
}
