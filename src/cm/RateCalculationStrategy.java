package cm;

import java.math.BigDecimal;

public interface RateCalculationStrategy {
    BigDecimal calculateRate(BigDecimal totalCost);
}
