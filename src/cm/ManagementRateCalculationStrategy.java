package cm;

import java.math.BigDecimal;

public class ManagementRateCalculationStrategy implements RateCalculationStrategy {
    private static final BigDecimal minPayable = new BigDecimal("5.00");

    @Override
    public BigDecimal calculateRate(BigDecimal totalCost) {
        return totalCost.max(minPayable);
    }
}
