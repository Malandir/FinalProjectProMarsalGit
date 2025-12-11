package cz.itnetwork.entity.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PersonStatistics {

    private Long personId = -1L;
    private String personName = "";
    private BigDecimal revenue;

}
