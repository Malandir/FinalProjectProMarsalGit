package cz.itnetwork.entity.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class InvoiceStatistics {

    private BigDecimal currentYearSum;
    private BigDecimal allTimeSum;
    private Long invoicesCount;

}
